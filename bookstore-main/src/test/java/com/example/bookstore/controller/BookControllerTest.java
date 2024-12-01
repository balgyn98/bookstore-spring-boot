package com.example.bookstore.controller;

import com.example.bookstore.config.TestcontainersConfig;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.core.ParameterizedTypeReference;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
public class BookControllerTest extends TestcontainersConfig{
    private BookRepository repository;
    private TestRestTemplate restTemplate;

    @Autowired
    public BookControllerTest(BookRepository repository, TestRestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));

        Book book1 = new Book("book1", "author1", new Date(), 1234);
        Book book2 = new Book("book2", "author2", new Date(), 1296);

        List<Book> books = Arrays.asList(book1, book2);

        repository.saveAll(books);
    }

    @Test
    void shouldFindAllBooks() {

        ResponseEntity<List<Book>> response = restTemplate.exchange(
                "/library/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        List<Book> books = response.getBody();
        assertEquals(2, books.size());
        assertEquals("author1", books.get(0).getAuthor());

    }

    @Test
    void shouldGetBooksById() {
        int bookId = 1;
        String url = String.format("http://localhost:%d/library/%d", port, bookId);

        ResponseEntity<Book> response = restTemplate.exchange(
                url, HttpMethod.GET, null, Book.class);

        Book book = response.getBody();

        assertNotNull(book);
        assertEquals(bookId, book.getId());
        assertEquals("author1", book.getAuthor());
        assertEquals(1234, book.getPrice());

    }
}
