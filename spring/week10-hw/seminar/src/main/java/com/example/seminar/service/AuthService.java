package com.example.seminar.service;

import com.example.seminar.dto.AuthDtos.*;
import com.example.seminar.entity.*;
import com.example.seminar.repository.*;
import com.example.seminar.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;

    public void signup(SignupRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");

        User user = User.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    public TokenResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();

        String access = jwtProvider.createAccessToken(user.getEmail(), user.getRole());
        String refresh = jwtProvider.createRefreshToken(user.getEmail());

        refreshTokenRepository.save(RefreshToken.builder()
                .userEmail(user.getEmail())
                .token(refresh)
                .build());

        return TokenResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .tokenType("Bearer")
                .build();
    }

    public TokenResponse reissue(String refreshToken) {
        if (!jwtProvider.validate(refreshToken))
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");

        String email = jwtProvider.getEmail(refreshToken);
        RefreshToken saved = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("DB에 토큰이 존재하지 않습니다."));

        if (!saved.getToken().equals(refreshToken))
            throw new IllegalArgumentException("리프레시 토큰 불일치");

        User user = userRepository.findByEmail(email).orElseThrow();
        String newAccess = jwtProvider.createAccessToken(email, user.getRole());
        String newRefresh = jwtProvider.createRefreshToken(email);

        saved.setToken(newRefresh);
        refreshTokenRepository.save(saved);

        return TokenResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .tokenType("Bearer")
                .build();
    }
}
