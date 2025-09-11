package com.example.spring6whw.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // [JPA] 가장 비싼 상품 Top10
    List<Product> findTop10ByOrderByPriceDesc();

    // [JPQL] 가격 <= :maxPrice 이고 재고 많은 순 Top N
    @Query("select p from Product p where p.price <= :maxPrice order by p.stock desc")
    List<Product> findCheapAndHighStock(int maxPrice, Pageable pageable);
}
