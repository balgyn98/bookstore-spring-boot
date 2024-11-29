package com.example.bookstore.repository;


import com.example.bookstore.entity.Order;
import com.example.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order, Integer> {
}