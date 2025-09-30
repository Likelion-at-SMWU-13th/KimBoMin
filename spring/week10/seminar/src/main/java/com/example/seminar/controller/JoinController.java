package com.example.seminar.controller;

import com.example.seminar.dto.JoinDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.seminar.service.JoinService;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);

        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }
}
