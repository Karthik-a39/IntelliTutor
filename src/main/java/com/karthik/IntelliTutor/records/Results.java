package com.karthik.IntelliTutor.records;

import lombok.Data;

import java.util.List;


public record Results (
    List<QuestionResult> results,
    String difficulty,
    String topic,
    int totalScore,
    String strength,
    String weakPoint,
    int totalQuestions,
    String nextTopic,
    String grade,
    Double percentage
){};
