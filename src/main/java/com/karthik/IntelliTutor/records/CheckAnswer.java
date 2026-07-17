package com.karthik.IntelliTutor.records;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public record CheckAnswer(
        List<Answers> answers
) {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoginRequest {

        @NotNull(message = "Email must be Filled")
        private String email;

        @NotNull(message = "Password must be filled with atleast 6 characters")
        private String password;

    }
};
