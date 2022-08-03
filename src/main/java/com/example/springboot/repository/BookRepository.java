package com.example.springboot.repository;

import com.example.springboot.modal.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    List<Book> books = new ArrayList<>();
    public BookRepository(){
        books.add(new Book("1","Data Structure",250.5));
        books.add(new Book("2","Algorithm",300.5));
        books.add(new Book("3","DBMS",200.5));
        books.add(new Book("4","Software Engineering",150.5));

    }
    public List<Book> getBooks() {
        return books;
    }
}
