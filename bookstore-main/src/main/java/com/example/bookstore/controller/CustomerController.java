package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public Customer login(@RequestBody String email) {
        String sanitized = email.replaceAll("^\"|\"$", "");
        return customerRepository.findFirstByEmailLike(sanitized)
                .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
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
