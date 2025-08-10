package com.example.springweek04hw.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String title, String author) {
        super("Duplicate book: " + title + " / " + author);
    }
}
