package com.karthik.IntelliTutor.records;


import java.util.List;

public record QuizQuestion(
        Integer id,
        String question,
        List<String> options
) {
}