package com.karthik.IntelliTutor.records;

import lombok.Builder;

@Builder
public record QuizAttemptRequest(

        String topic,
        String difficulty,
        Integer totalQuestions,
        Integer correctAnswers,
        Integer wrongAnswers,
        Double percentage,
        String grade

) {
}