package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import com.example.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Integer> {
    Optional<Book> findById(int id);
}
