package com.karthik.IntelliTutor.records;

import java.util.List;

public record SummaryResponse(
        String summary,
        List<String> topicsLearned,
        List<String> quizTopics,
        List<String> strengths,
        List<String> weaknesses,
        List<String> nextTopics,
        String feedback
) {
}