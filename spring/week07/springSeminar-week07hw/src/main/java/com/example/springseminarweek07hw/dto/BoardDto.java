package com.example.springseminarweek07hw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {
    public record CreateReq(
            @NotBlank String title,
            @NotBlank String content,
            @NotNull Long authorId
    ) {}

    public record CommentRes(Long id, String content, Long authorId, String authorUsername,
                             LocalDateTime createdAt) {}

    public record DetailRes(Long id, String title, String content,
                            Long authorId, String authorUsername,
                            LocalDateTime createdAt, LocalDateTime updatedAt,
                            List<CommentRes> comments) {}
}
