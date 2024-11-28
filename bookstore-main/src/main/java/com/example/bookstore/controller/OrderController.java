package com.example.bookstore.controller;

import com.example.bookstore.services.OrderService;
import com.example.bookstore.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> orders() {
        return orderService.getOrdersList();
    }

    @PostMapping("/save-order")
    public boolean saveOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.saveOrder(
                orderRequest.getUserBooksList(),
                orderRequest.getCustomer()
        );
    }
}
