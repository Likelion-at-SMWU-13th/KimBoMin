package com.example.seminar.repository;
import com.example.seminar.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {}
