package com.example.seminar.service;

import com.example.seminar.dto.SignupRequest;
import com.example.seminar.entity.User;
import com.example.seminar.repository.UserRepository;
import com.example.seminar.exception.HttpStatus;
import Exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupRequest request) {
        // 아이디 중복 검사
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new CustomException(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다.");
        }

        // 이메일 도메인 검사
        if (!request.getEmail().endsWith("@sookmyung.ac.kr")) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "숙명여대 이메일(@sookmyung.ac.kr)만 가입 가능합니다.");
        }

        // 저장
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
    }
}
