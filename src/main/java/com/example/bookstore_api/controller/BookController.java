package com.example.bookstore_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Rightmove!";
    }
}

