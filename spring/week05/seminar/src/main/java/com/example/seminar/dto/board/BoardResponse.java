package com.example.seminar.dto.board;
import com.example.seminar.entity.BoardEntity;
import lombok.*; import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardResponse {
    private Long id; private String name; private String description; private LocalDateTime createdAt;
    public static BoardResponse from(BoardEntity e){
        return BoardResponse.builder()
                .id(e.getId()).name(e.getName()).description(e.getDescription())
                .createdAt(e.getCreatedAt()).build();
    }
}
