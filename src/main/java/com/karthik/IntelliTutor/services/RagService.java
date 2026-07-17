package com.karthik.IntelliTutor.services;

import com.google.genai.Chat;
import com.karthik.IntelliTutor.entities.AuthEntity;
import com.karthik.IntelliTutor.entities.QuizAttempt;
import com.karthik.IntelliTutor.records.CheckAnswer;
import com.karthik.IntelliTutor.records.QuizQuestions;
import com.karthik.IntelliTutor.records.Results;
import com.karthik.IntelliTutor.records.SummaryResponse;
import com.karthik.IntelliTutor.repos.AuthRepo;
import com.karthik.IntelliTutor.repos.QuizAtttemptRepo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RagService {

    private final ChatClient chatClient;
    private final QuizAtttemptRepo repo;
    private final AuthRepo authRepo;

    public RagService(ChatClient.Builder builder, ChatMemory memory,QuizAtttemptRepo repo,AuthRepo authRepo){
        this.chatClient=builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
        this.repo=repo;
        this.authRepo=authRepo;
    }


    public String AskQuestion(String conversationId,String question){

        String system="You are A Rag System based on the User Prompt and " +
                "relative chuck from context give answer in short and sweet";
        return chatClient.prompt()
                .system(system)
                .user(question)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId))
                .call()
                .content();
    }

    public QuizQuestions takeQuiz(String conversationId, String topic,String noOfQuestions) {
        String systemPrompt="You are A Rag System based on the user {topic} name you should" +
                "generate {noOfQuestions} interview questions with options having options like A B C D " +
                "with unique question Ids for each questions also mention the difficulty of the quiz";
        return chatClient.prompt()
                .system(s->s.text(systemPrompt)
                                             .param("topic",topic)
                                             .param("noOfQuestions",noOfQuestions) )
                .user(topic)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId))
                .call()
                .entity(QuizQuestions.class);
    }


    public Results checkAnswers(String conversationId, CheckAnswer checkAnswer) {
        String systemPrompt=" You are a RAG System.\n" +
                "\n" +
                "            Evaluate the user's answers.\n" +
                "\n" +
                "            Return:\n" +
                "            - topic\n" +
                "            - difficulty\n" +
                "            - totalQuestions\n" +
                "            - totalScore\n" +
                "            - percentage\n" +
                "            - grade\n" +
                "            - strength\n" +
                "            - weakPoint\n" +
                "            - nextTopic\n" +
                "            - results";
        Results results= chatClient.prompt()
                .system(s->s.text(systemPrompt))
                .user(String.valueOf(checkAnswer))
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId))
                .call()
                .entity(Results.class);


        AuthEntity user = authRepo.findByEmail(conversationId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        QuizAttempt quizAttempt=QuizAttempt.builder()
                .grade(results.grade())
                .difficulty(results.difficulty())
                .topic(results.topic())
                .nextTopic(results.nextTopic())
                .strength(results.strength())
                .weakPoint(results.weakPoint())
                .attemptedAt(LocalDateTime.now())
                .user(user)
                .totalQuestions(results.totalQuestions())
                .totalScore(results.totalScore())
                .build();

        repo.save(quizAttempt);

        return results;
    }

    public SummaryResponse getSummary(String conversationId){
        String systemPrompt="You are IntelliTutor, an AI learning assistant.\n" +
                "\n" +
                "Using the conversation history stored in chat memory, generate today's learning summary.\n" +
                "\n" +
                "Return ONLY valid JSON matching the SummaryResponse schema.\n" +
                "\n" +
                "The summary should include:\n" +
                "\n" +
                "1. Overall summary of what the user learned.\n" +
                "2. Topics covered.\n" +
                "3. Quiz topics attempted.\n" +
                "4. Quiz performance summary.\n" +
                "5. Strong areas.\n" +
                "6. Weak areas.\n" +
                "7. Recommended next topics to study.\n" +
                "8. Overall feedback.";

        return chatClient.prompt()
                .system(s->s.text(systemPrompt))
                .user("Generate Summary for today Work")
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId))
                .call()
                .entity(SummaryResponse.class);
    }
}
