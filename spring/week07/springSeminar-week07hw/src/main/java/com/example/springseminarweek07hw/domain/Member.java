package com.example.springseminarweek07hw.domain;

import com.example.springseminarweek07hw.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "members")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String username;

    @Column(length = 60)
    private String displayName;

    @OneToMany(mappedBy = "author")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    public void addBoard(Board b){ boards.add(b); b.setAuthor(this); }
    public void addComment(Comment c){ comments.add(c); c.setAuthor(this); }
}
