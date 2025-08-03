package com.example.seminarhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/books")
    public String books(){
        return "books.html";
    }
}
