package com.example.springweek04hw.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book not found. id=" + id);
    }
}
