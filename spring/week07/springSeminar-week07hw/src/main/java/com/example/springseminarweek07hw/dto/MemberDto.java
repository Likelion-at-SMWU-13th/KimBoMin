package com.example.springseminarweek07hw.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberDto {
    public record CreateReq(@NotBlank String username, String displayName) {}
    public record SimpleRes(Long id, String username, String displayName) {}
}
