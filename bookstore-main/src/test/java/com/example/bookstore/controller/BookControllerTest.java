package com.example.bookstore.controller;

import com.example.entity.Book;
import com.example.repository.BookDAO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    BookDAO bookDAO;

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

        Mockito.when(bookDAO.findAll()).thenReturn(books);

        List<Book> result = bookController.findAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("book1", result.get(0).getName());
        assertEquals("author1", result.get(0).getAuthor());
    }

    @Test
    public void findBookById(){

        Mockito.when(bookDAO.findById(1)).thenReturn(book);

        Book result = bookController.findBookById(1);

        assertNotNull(result);
        assertEquals("book1", result.getName());
        assertEquals("author1", result.getAuthor());
    }

}
