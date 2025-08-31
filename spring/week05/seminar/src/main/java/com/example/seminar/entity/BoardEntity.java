package com.example.seminar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(
            targetEntity = PostEntity.class,
            fetch = FetchType.LAZY,
            mappedBy = "boardEntity"
    )
    private List<PostEntity> postEntityList = new ArrayList<>();
}

