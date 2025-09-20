package com.example.seminar.service;

import com.example.seminar.dto.SignupRequest;
import com.example.seminar.Exception.HttpStatus;
import com.example.seminar.Exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    // 예시용 (실제로는 DB 사용)
    private final Set<String> userIds = new HashSet<>();

    public void signup(SignupRequest request) {
        // 1. 아이디 중복 검사
        if (userIds.contains(request.getUsername())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.");
        }

        // 2. 이메일 도메인 검사
        if (!request.getEmail().endsWith("@sookmyung.ac.kr")) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "숙명여대 이메일(@sookmyung.ac.kr)만 가입 가능합니다.");
        }

        // 회원 저장 (DB에 저장한다고 가정)
        userIds.add(request.getUsername());
    }
}
