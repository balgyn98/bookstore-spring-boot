package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public Customer login(@RequestBody String email) {
        return customerService.findUserByEmail(email);
    }

    @PostMapping("/register")
    public Customer register(@RequestBody RegisterRequest registerRequest) {
        return customerRepository.save(new Customer(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        ));
    }

}
