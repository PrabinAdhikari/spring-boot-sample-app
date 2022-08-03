package com.example.springboot.controller;

import com.example.springboot.modal.Book;
import com.example.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    BookService bookService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/books")
    public List<Book> getBooks() {
        List<Book> books = bookService.getBooks();
        return books;
    }

}