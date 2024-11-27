package com.example.bookstore.controller;

import com.example.entity.Book;
import com.example.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    BookDAO bookDAO;

    @GetMapping("/allbooks")
    public List<Book> findAllBooks(){
        return bookDAO.findAll();
    }

    @GetMapping("/bookById/{bookId}")
    public Book findBookById(@PathVariable("bookId") int bookId){
        return bookDAO.findById(bookId);
    }
}
