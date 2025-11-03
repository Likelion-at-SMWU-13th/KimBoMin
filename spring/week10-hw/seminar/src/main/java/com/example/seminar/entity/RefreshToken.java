package com.example.seminar.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RefreshToken {
    @Id
    private String userEmail;

    @Column(nullable = false, length = 512)
    private String token;
}
