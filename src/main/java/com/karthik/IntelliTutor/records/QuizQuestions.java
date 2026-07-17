package com.karthik.IntelliTutor.records;

import java.util.List;

public record QuizQuestions(
        String topic,
        String difficulty,
        Integer totalQuestions,
        String estimatedTime,
        List<QuizQuestion> questions
) {
}