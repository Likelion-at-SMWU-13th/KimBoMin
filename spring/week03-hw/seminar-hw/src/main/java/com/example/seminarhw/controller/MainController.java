package com.example.seminarhw.controller;

import com.example.seminarhw.model.Book;
import com.example.seminarhw.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }
}
