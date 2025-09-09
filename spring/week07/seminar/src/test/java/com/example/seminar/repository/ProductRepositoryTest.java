package com.example.seminar.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.seminar.entity.Product;
import com.example.seminar.entity.QProduct;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setUp(){
        productRepository.save(new Product("펜", 1000, 100));
        productRepository.save(new Product("연필", 500, 200));
        productRepository.save(new Product("노트", 2000, 150));
        productRepository.save(new Product("지우개", 300, 300));
        productRepository.save(new Product("자", 700, 250));
    }

    @Test
    void queryDslTest() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList =
                query.selectFrom(qProduct)
                        .where(qProduct.name.eq("펜"))
                        .orderBy(qProduct.price.asc())
                        .fetch();

        for (Product product : productList){
            System.out.println("---------------");
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println("---------------");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList =
                query.select(qProduct.name)
                        .from(qProduct)
                        .where(qProduct.name.eq("연필"))
                        .orderBy(qProduct.price.asc())
                        .fetch();

        for (String product : productList){
            System.out.println("---------------");
            System.out.println("Product Name : " + product);
            System.out.println("---------------");
        }

        List<Tuple> tupleList =
                query.select(qProduct.name, qProduct.price)
                        .from(qProduct)
                        .where(qProduct.name.eq("펜"))
                        .orderBy(qProduct.price.asc())
                        .fetch();

        for (Tuple product : tupleList){
            System.out.println("---------------");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Price : " + product.get(qProduct.price));
            System.out.println("---------------");
        }
    }
}