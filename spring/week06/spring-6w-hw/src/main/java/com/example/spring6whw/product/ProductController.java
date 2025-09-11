package com.example.spring6whw.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/jpa")
    public List<Product> jpaTop10ByPrice() {
        return productService.getTop10ByPrice();
    }


    @GetMapping("/jpql")
    public List<Product> jpqlCheapAndHighStockTop5() {
        return productService.getTop5CheapAndHighStock();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }
}
