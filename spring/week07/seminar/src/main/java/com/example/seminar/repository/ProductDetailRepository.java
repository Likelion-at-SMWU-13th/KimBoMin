package com.example.seminar.repository;

import com.example.seminar.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
