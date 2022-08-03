package com.example.springboot.service;

import com.example.springboot.modal.Book;
import com.example.springboot.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    private List<Book> expectedBooks = new ArrayList<>();
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService mockBookService;

    @Test
    public void getBooks(){
        createTestBooks();
        when(mockBookService.getBooks()).thenReturn(expectedBooks);
        List<Book>actualBooks = mockBookService.getBooks();
        assertEquals(expectedBooks,actualBooks);
    }

    private void createTestBooks() {
        expectedBooks.add(new Book("1","a",1.5));
        expectedBooks.add(new Book("3","c",3.5));
        expectedBooks.add(new Book("2","b",2.5));
    }
}
