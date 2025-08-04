package com.example.seminarhw.service;

import com.example.seminarhw.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book){
        bookList.add(book);
    }

    public List<Book> findAll() {
        return bookList;
    }
}
