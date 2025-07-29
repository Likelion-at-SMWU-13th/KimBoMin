package com.example.seminar2.controller;

import com.example.seminar2.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;

public class ProductsController {
    
    private ProductService productionService;
    
    public ProductsController(ProductService productService){
        this.productionService = productService;
    }
    
    @RequestMapping("/products")
    public String viewProducts(Model model){
        var products = productionService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }
}
