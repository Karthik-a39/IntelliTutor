package com.karthik.IntelliTutor.controllers;

import com.karthik.IntelliTutor.records.CheckAnswer;
import com.karthik.IntelliTutor.records.QuizQuestions;
import com.karthik.IntelliTutor.records.Results;
import com.karthik.IntelliTutor.records.SummaryResponse;
import com.karthik.IntelliTutor.services.DocumentIngestService;
import com.karthik.IntelliTutor.services.RagService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/rag/services")
public class RagController {

    private final ChatClient chatClient;
    private final DocumentIngestService documentIngestService;
    private final RagService ragService;
    public RagController(ChatClient.Builder builder, DocumentIngestService documentIngestService,
                         ChatMemory memory,RagService ragService){
        this.chatClient=builder.
                defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
        this.documentIngestService=documentIngestService;
        this.ragService=ragService;
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        Resource resource = new ByteArrayResource(file.getBytes());
        int chunkCount = documentIngestService.readPdf(resource);
        return "Ingested " + chunkCount + " chunks from " + file.getOriginalFilename();
    }

    @GetMapping("/ask")
    public String ask(Authentication authentication, String question){
        String conversationId=authentication.getName();
        return ragService.AskQuestion(conversationId,question);
    }


    @GetMapping("/quiz")
    public QuizQuestions takeQuiz(Authentication authentication,@RequestParam String topic,@RequestParam(defaultValue = "5") String noOfQuestions){
        String conversationId=authentication.getName();
        return ragService.takeQuiz(conversationId,topic,noOfQuestions);
    }

    @PostMapping("/checkAnswers")
    public Results checkAnswers(Authentication authentication, @RequestBody CheckAnswer checkAnswer){
        String conversationId=authentication.getName();
        return ragService.checkAnswers(conversationId,checkAnswer);
    }

    @GetMapping("/summary")
    public SummaryResponse getSummary(Authentication authentication){
        String conversationId=authentication.getName();
        return ragService.getSummary(conversationId);
    }
}
