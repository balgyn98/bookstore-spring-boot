package com.example.bookstore.repository;

import com.example.bookstore.entity.Customer;
import com.example.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Integer> {
    Optional<Customer> findFirstByEmailLike(String email);
}
