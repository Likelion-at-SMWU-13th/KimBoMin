package com.example.springweek04hw.api;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String code;
    private String message;
    private String path;

    public ErrorResponse() {}
    public ErrorResponse(int status, String code, String message, String path) {
        this.status = status; this.code = code; this.message = message; this.path = path;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getCode() { return code; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
}
