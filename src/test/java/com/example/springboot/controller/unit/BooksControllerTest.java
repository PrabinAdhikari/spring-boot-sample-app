package com.example.springboot.controller.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springboot.controller.BooksController;
import com.example.springboot.modal.Book;
import com.example.springboot.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @MockBean
    private BookService mockBookService;

    @Autowired
    private MockMvc mvc;
    private List<Book> expectedBooks = new ArrayList<>();
    private String expectedBooksAsJsonString = "[{\"id\":\"1\",\"name\":\"a\",\"price\":1.0},{\"id\":\"3\",\"name\":\"c\",\"price\":3.0},{\"id\":\"2\",\"name\":\"b\",\"price\":2.0}]";

    @BeforeEach
    public void init(){
        expectedBooks.add(new Book("1","a",1.0));
        expectedBooks.add(new Book("3","c",3.0));
        expectedBooks.add(new Book("2","b",2.0));

    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }
    @Test
    public void getBooks() throws Exception {
        when (mockBookService.getBooks()).thenReturn(expectedBooks);
        this.mvc.perform(MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedBooksAsJsonString));
    }
}