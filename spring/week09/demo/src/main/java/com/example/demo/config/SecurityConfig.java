package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // H2 콘솔은 CSRF 검사 제외 + frame 허용
                .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .headers(h -> h.frameOptions(f -> f.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        // H2 콘솔 전체 허용
                        .requestMatchers(PathRequest.toH2Console()).permitAll());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/my", true)
                        .permitAll()
                );

        return http.build();

    }
}



