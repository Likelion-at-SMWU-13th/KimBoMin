package com.example.seminar.member.service;

import com.example.seminar.member.entity.Member;
import com.example.seminar.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findMemberAll() {
        return memberRepository.findAll();
    }

    // JPA
    public Member findByJpaOne(String username) {
        return memberRepository.findByUsername(username);
    }

    public List<Member> findByJpaAll(String username, int age) {
        return memberRepository.findByUsernameAndAgeGreaterThanEqual(username, age);
    }

    // JPQL
    public List<Member> findByJPQL(String username, int age) {
        return memberRepository.findMembersByUsernameAndMinAge(username, age);
    }
}
