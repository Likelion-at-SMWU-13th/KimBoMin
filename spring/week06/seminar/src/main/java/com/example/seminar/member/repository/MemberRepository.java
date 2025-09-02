package com.example.seminar.member.repository;

import com.example.seminar.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // JPA
    Member findByUsername(String username);
    List<Member> findByUsernameAndAgeGreaterThanEqual(String username, int age);

    // JPQL
    @Query("SELECT m FROM Member m WHERE m.username = :username AND m.age >= :age")
    List<Member> findMembersByUsernameAndMinAge(@Param("username") String username, @Param("age") int age);
}
