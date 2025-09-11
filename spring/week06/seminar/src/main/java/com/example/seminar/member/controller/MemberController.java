package com.example.seminar.member.controller;

import com.example.seminar.member.entity.Member;
import com.example.seminar.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 생성
    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @GetMapping("/members")
    public List<Member> readMemberAll() {
        return memberService.findMemberAll();
    }

    @GetMapping("/members/jpa/one")
    public Member jpaFindByUsername(@RequestParam String username) {
        return memberService.findByJpaOne(username);
    }

    @GetMapping("/members/jpa/all")
    public List<Member> jpaFindMembers(@RequestParam String username, @RequestParam int age) {
        return memberService.findByJpaAll(username, age);
    }

    @GetMapping("/members/jpql")
    public List<Member> jpql(@RequestParam String username, @RequestParam int age) {
        return memberService.findByJPQL(username, age);
    }
}
