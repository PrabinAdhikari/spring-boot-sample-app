package com.example.springboot.controller.integration;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksControllerIT {

    @LocalServerPort
    private int port;

    private URL base;
    private String expectedBooksAsJsonString = "[{\"id\":\"1\",\"name\":\"Data Structure\",\"price\":250.5},{\"id\":\"2\",\"name\":\"Algorithm\",\"price\":300.5},{\"id\":\"3\",\"name\":\"DBMS\",\"price\":200.5},{\"id\":\"4\",\"name\":\"Software Engineering\",\"price\":150.5}]";

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }
    @Test
    public void getBooks(){
        ResponseEntity<String> response = template.getForEntity(base.toString()+"/books",String.class);
        assertThat(response.getBody()).isEqualTo(expectedBooksAsJsonString);
    }
}