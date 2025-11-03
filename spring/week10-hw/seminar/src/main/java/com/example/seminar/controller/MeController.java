package com.example.seminar.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MeController {

    @GetMapping("/api/me")
    public Map<String, String> me(@AuthenticationPrincipal UserDetails user) {
        return Map.of("email", user.getUsername());
    }
}
