package com.example.seminar.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    // 1. CustomException 처리
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException e, HttpServletRequest request) {
        LOGGER.error("CustomException 발생, URI: {}, 메시지: {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatus().getMessage());
        map.put("code", Integer.toString(e.getHttpStatus().getCode()));
        map.put("message", e.getMessage());

        return ResponseEntity
                .status(e.getHttpStatus().getCode())
                .body(map);
    }

    // 2. DTO 유효성 검증 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())); // 필드명 : 메시지 매핑
        return ResponseEntity.badRequest().body(errors);
    }
}
