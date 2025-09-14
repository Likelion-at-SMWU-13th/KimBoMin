package com.example.springseminarweek07hw.repository;

import com.example.springseminarweek07hw.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {}
