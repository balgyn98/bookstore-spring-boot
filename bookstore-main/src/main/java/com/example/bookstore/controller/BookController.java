package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    private final CustomerService customerService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(CustomerService customerService, BookRepository bookRepository) {
        this.customerService = customerService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{bookId}")
    public Book findBookById(@PathVariable("bookId") int bookId){
        return customerService.findBookById(bookId);
    }
}
