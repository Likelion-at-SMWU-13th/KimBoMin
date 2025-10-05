package com.example.seminar.service;

import com.example.seminar.entity.User;
import com.example.seminar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user: " + email));
        // DB에 ROLE_USER 형태로 저장되어 있다면 접두사 제거해서 roles()에 넣어줌
        String role = u.getRole() != null ? u.getRole().replace("ROLE_", "") : "USER";
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(role)
                .build();
    }
}
