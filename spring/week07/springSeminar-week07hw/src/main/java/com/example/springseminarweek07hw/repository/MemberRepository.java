package com.example.springseminarweek07hw.repository;

import com.example.springseminarweek07hw.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
