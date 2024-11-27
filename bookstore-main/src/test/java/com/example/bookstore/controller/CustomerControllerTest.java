package com.example.bookstore.controller;

import com.example.entity.Customer;
import com.example.repository.CustomerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    CustomerDAO customerDAO;

    @InjectMocks
    CustomerController customerController;

    Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer("user1", "user1", "user1", 1976);
    }

    @Test
    public void login() {
        Mockito.when(customerDAO.findFirstByEmailLike("user1")).thenReturn(customer);

        Customer result = customerController.login("user1");

        assertNotNull(result);
        assertEquals("user1", result.getName());
        assertEquals(1976, result.getBalance());
    }

    @Test
    public void register() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("user1");
        registerRequest.setEmail("user1@example.com");
        registerRequest.setPassword("password");

        Customer savedCustomer = new Customer(
                "user1", "user1@example.com", "password", 10000);

        Mockito.doReturn(savedCustomer).when(customerDAO).save(Mockito.any(Customer.class));

        Customer result = customerController.register(registerRequest);

        assertNotNull(result);
        assertEquals("user1", result.getName());
        assertEquals("user1@example.com", result.getEmail());
        assertEquals(10000, result.getBalance());
    }
}
