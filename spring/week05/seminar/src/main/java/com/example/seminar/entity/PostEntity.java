package com.example.seminar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;

    @JoinColumn(name = "board_id")
    @ManyToOne(
            targetEntity = BoardEntity.class,
            fetch = FetchType.LAZY
    )
    private BoardEntity boardEntity;
}