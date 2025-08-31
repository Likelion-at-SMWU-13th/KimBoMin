package com.example.seminar.repository;

import com.example.seminar.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}