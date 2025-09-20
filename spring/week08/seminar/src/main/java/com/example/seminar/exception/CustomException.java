package com.example.seminar.exception;

public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CustomException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "httpStatus=" + httpStatus.getCode() + " " + httpStatus.getMessage() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}