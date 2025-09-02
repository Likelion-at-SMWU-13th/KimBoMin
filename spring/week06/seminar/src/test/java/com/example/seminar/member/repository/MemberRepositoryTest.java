package com.example.seminar.member.repository;

import com.example.seminar.member.entity.Member;
import com.example.seminar.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    // 더미 데이터 삽입
    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("민서", 30));
        memberRepository.save(new Member("상희", 22));
        memberRepository.save(new Member("지", 25));
        memberRepository.save(new Member("채민", 34));
        memberRepository.save(new Member("지윤", 28));
        memberRepository.save(new Member("민서", 23));
        memberRepository.save(new Member("상희", 34));
        memberRepository.save(new Member("지", 26));
        memberRepository.save(new Member("채민", 26));
        memberRepository.save(new Member("지윤", 53));
    }

    @Test
    void queryDslTest01() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        // 실습 1. username이 "민서"이고 나이가 25 이상인 회원 조회
        List<Member> memberList = query.selectFrom(qMember)
                .where(qMember.username.eq("민서")
                        .and(qMember.age.goe(25)))
                .fetch();

        for (Member member : memberList){
            System.out.println("---------------");
            System.out.println("Member Id : " + member.getId());
            System.out.println("Member Username : " + member.getUsername());
            System.out.println("Member Age : " + member.getAge());
            System.out.println("---------------");
        }
    }

    @Test
    void queryDslTest02() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        // 실습 2. 평균 나이 계산
        Double avgAge = query.select(qMember.age.avg())
                        .from(qMember)
                                .fetchOne();


        System.out.println("Member avgAge : " + avgAge);
    }

    @Test
    void queryDslTest03() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        // 실습 3. 이름에 "민"이 포함되고, 나이가 25 이상인 회원 조회, 나이 내림차순
        List<Member> memberList = query.selectFrom(qMemeber)
                .where(qMember.username.contains("민"))
                    .and(qMember.age.goe(25)))
                .orderBy(qMember.age.desc())
                .fetch();

        for (Member member : memberList){
            System.out.println("---------------");
            System.out.println("Member Id : " + member.getId());
            System.out.println("Member Username : " + member.getUsername());
            System.out.println("Member Age : " + member.getAge());
            System.out.println("---------------");
        }
    }
}