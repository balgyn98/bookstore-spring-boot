package com.example.bookstore.services;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CustomerService {

    private final BookRepository bookRepository;

    @Autowired
    public CustomerService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book findBookById(int bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with bookId: " + bookId));
    }


}
