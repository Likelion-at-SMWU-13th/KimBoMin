package com.example.seminar.dto;

import jakarta.validation.constraints.*;
import lombok.*;

public class AuthDtos {

    @Getter @Setter
    public static class SignupRequest {
        @Email @NotBlank private String email;
        @NotBlank @Size(min = 6, max = 50) private String password;
    }

    @Getter @Setter
    public static class LoginRequest {
        @Email @NotBlank private String email;
        @NotBlank private String password;
    }

    @Getter
    @Builder
    public static class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
        private final String tokenType;
    }

    @Getter @Setter
    public static class ReissueRequest {
        @NotBlank private String refreshToken;
    }
}
