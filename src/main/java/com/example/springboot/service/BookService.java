package com.example.springboot.service;

import com.example.springboot.modal.Book;
import com.example.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }
}
