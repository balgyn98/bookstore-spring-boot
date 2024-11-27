package com.example.bookstore.controller;

import com.example.bookstore.services.OrderService;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    public List<Order> orders() {
        return orderService.getOrdersList();
    }

    @PostMapping("/saveOrder")
    public boolean saveOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.saveOrder(
                orderRequest.getUserBooksList(),
                orderRequest.getCustomer()
        );
    }
}
