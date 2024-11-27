package com.example.bookstore.controller;

import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    CustomerDAO customerDAO;

    @PostMapping("/login")
    public Customer login(@RequestBody String email) {
        return customerDAO.findFirstByEmailLike(email);
    }

    @PostMapping("/register")
    public Customer register(@RequestBody RegisterRequest registerRequest) {
        return customerDAO.save(new Customer(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                10000
        ));
    }

}
