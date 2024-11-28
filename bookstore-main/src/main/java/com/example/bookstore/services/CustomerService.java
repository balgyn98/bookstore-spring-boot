package com.example.bookstore.services;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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


}
