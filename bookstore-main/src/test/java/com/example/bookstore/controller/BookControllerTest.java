package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookController bookController;

    Book book;

    @BeforeEach
    public void setup() {
        Date date = new Date();
        book = new Book("book1", "author1", date, 1000);
    }

    @Test
    public void findAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookController.findAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("book1", result.get(0).getName());
        assertEquals("author1", result.get(0).getAuthor());
    }

    @Test
    public void findBookById(){

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(book));

        Book result = bookController.findBookById(1);

        assertNotNull(result);
        assertEquals("book1", result.getName());
        assertEquals("author1", result.getAuthor());
    }

}
