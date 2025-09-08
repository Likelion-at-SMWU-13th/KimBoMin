package com.example.spring6whw.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getTop10ByPrice() {
        return productRepository.findTop10ByOrderByPriceDesc();
    }

    public List<Product> getTop5CheapAndHighStock() {
        return productRepository.findCheapAndHighStock(2000, PageRequest.of(0, 5));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }
}
