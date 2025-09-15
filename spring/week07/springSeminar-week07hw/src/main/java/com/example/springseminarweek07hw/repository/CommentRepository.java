package com.example.springseminarweek07hw.repository;

import com.example.springseminarweek07hw.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
