package com.example.springweek04hw.controller;

import com.example.springweek04hw.model.Book;
import com.example.springweek04hw.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService svc;

    public BookController(BookService svc) {
        this.svc = svc;
        // 더미 데이터 초기화는 API 쪽에서 처리하거나 필요 시 수동으로만 호출
        // this.svc.initDummyIfEmpty();  // 필요하면 주석 해제
    }

    // 목록 페이지
    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", svc.findAll());
        model.addAttribute("book", new Book()); // 추가 폼 바인딩용
        return "books"; // templates/books.html
    }

    // 도서 추가
    @PostMapping
    public String add(@ModelAttribute("book") Book book) {
        svc.add(book);
        return "redirect:/books";
    }
}
