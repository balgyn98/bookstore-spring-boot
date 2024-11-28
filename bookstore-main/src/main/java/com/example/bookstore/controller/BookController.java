package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/book-by-id/{bookId}")
    public Book findBookById(@PathVariable("bookId") int bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with bookId: " + bookId));
    }
}
