package com.example.seminar.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity-ms}")
    private long accessTokenValidity;

    @Value("${jwt.refresh-token-validity-ms}")
    private long refreshTokenValidity;

    private Key key;

    @PostConstruct
    public void init() {
        // secret 길이는 충분히 길어야 함(최소 256bit). application.yml에 긴 값 사용!
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createAccessToken(String email, String role) {
        return createToken(email, role, accessTokenValidity);
    }

    public String createRefreshToken(String email) {
        return createToken(email, null, refreshTokenValidity);
    }

    private String createToken(String email, String role, long validityMs) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityMs);

        JwtBuilder b = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256);

        if (role != null) b.claim("role", role);
        return b.compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
