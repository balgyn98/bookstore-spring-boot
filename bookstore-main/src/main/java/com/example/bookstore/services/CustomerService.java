package com.example.bookstore.services;

import com.example.bookstore.controller.RegisterRequest;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findUserByEmail(String email) {
        String sanitized = email.replaceAll("^\"|\"$", "");
        return customerRepository.findFirstByEmailLike(sanitized).orElse(null);
    }

    public Customer register(RegisterRequest registerRequest) {
        return customerRepository.save(new Customer(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        ));
    }

}
