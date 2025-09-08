package com.example.spring6whw.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // [JPA]
    @GetMapping("/jpa")
    public List<Product> jpaTop10ByPrice() {
        return productService.getTop10ByPrice();
    }

    // [JPQL]
    @GetMapping("/jpql")
    public List<Product> jpqlCheapAndHighStockTop5() {
        return productService.getTop5CheapAndHighStock();
    }
}
