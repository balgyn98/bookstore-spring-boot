package com.example.bookstore.controller;

import com.example.bookstore.services.OrderService;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    Customer customer;
    Book book;

    @BeforeEach
    public void setup() {
        customer = new Customer("user1", "user1", "user1");

        Date date = new Date();
        book = new Book("book1", "author1", date, 1000);
    }

    @Test
    public void login() {
        List<Book> books = new ArrayList<>();
        books.add(book);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setCustomer(customer);
        orderRequest.setUserBooksList(books);

        Mockito.doReturn(true).when(orderService).saveOrder(orderRequest.getUserBooksList(), orderRequest.getCustomer());

        boolean result = orderController.saveOrder(orderRequest);

        assertTrue(result);
    }


}
