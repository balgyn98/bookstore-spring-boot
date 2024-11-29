package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerController customerController;

    Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer("user1", "user1", "user1");
    }

    @Test
    public void login() {
        Mockito.when(customerRepository.findFirstByEmailLike("user1")).thenReturn(Optional.ofNullable(customer));

        Customer result = customerController.login("user1");

        assertNotNull(result);
        assertEquals("user1", result.getName());
    }

    @Test
    public void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("user1");
        registerRequest.setEmail("user1@example.com");
        registerRequest.setPassword("password");

        Customer savedCustomer = new Customer(
                "user1", "user1@example.com", "password");

        Mockito.doReturn(savedCustomer).when(customerRepository).save(Mockito.any(Customer.class));

        Customer result = customerController.register(registerRequest);

        assertNotNull(result);
        assertEquals("user1", result.getName());
        assertEquals("user1@example.com", result.getEmail());
    }
}
