package com.example.springseminarweek07hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDto {
    public record CreateReq(
            @NotBlank String content,
            @NotNull Long boardId,
            @NotNull Long authorId
    ) {}
}
