package com.karthik.IntelliTutor.records;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record QuestionResult(
        int questionId,
        String question,
        String userAnswer,
        String correctAnswer,
        boolean correct,
        String explanation
) {
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @Builder
    public static class RegisterRequest {

        private Long userId;

        @NotNull(message = "userName must be filled")
        private String userName;

        @NotNull(message = "Email must be Filled")
        private String email;

        @NotNull(message = "Password must be filled with atleast 6 characters")
        private String password;

        private LocalDateTime createdAt;

    }
}