package com.example.seminar.repository;

import com.example.seminar.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
