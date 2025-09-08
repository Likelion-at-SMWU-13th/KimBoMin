package com.example.spring6whw.product;

import com.example.spring6whw.config.QuerydslConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // H2 메모리 사용
class ProductQuerydslTest {

    @Autowired EntityManager em;
    @Autowired JPAQueryFactory queryFactory;

    @BeforeEach
    void setUp() {
        em.persist(new Product(null, "볼펜",   1000, 50));
        em.persist(new Product(null, "형광펜", 1200, 80));
        em.persist(new Product(null, "연필",    500, 100));
        em.persist(new Product(null, "샤프",   1500, 200));
        em.persist(new Product(null, "펜촉세트", 700, 60));
        em.flush();
    }

    @Test
    @DisplayName("[QueryDSL] 이름에 '펜' 포함 + 가격 오름차순 Top10")
    void querydsl_pen_and_cheap_top10() {
        QProduct p = QProduct.product;

        List<Product> result = queryFactory
                .selectFrom(p)
                .where(p.name.contains("펜"))
                .orderBy(p.price.asc())
                .limit(10)
                .fetch();

        assertThat(result).isNotEmpty();
    }
}
