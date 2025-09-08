package com.example.spring6whw.product;

import com.example.spring6whw.config.QuerydslConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
class ProductQuerydslTest {

    @Autowired EntityManager em;
    @Autowired JPAQueryFactory queryFactory;

    @Test
    void querydsl_pen_and_cheap_top10() {
        QProduct p = QProduct.product;

        List<Product> result = queryFactory
                .selectFrom(p)
                .where(p.name.contains("펜"))
                .orderBy(p.price.asc())
                .limit(10)
                .fetch();

        assertThat(result.size()).isBetween(0, 10);
    }
}
