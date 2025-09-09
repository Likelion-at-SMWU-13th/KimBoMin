package com.example.seminar.repository;

import com.example.seminar.entity.Provider;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
