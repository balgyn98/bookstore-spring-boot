package com.example.bookstore.services;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBookById(int bookId){
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }
}
