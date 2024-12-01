package com.example.bookstore.controller;

import com.example.bookstore.config.TestcontainersConfig;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CustomerControllerTest extends TestcontainersConfig {
    private CustomerRepository repository;
    private TestRestTemplate restTemplate;

    @Autowired
    public CustomerControllerTest(CustomerRepository repository, TestRestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @LocalServerPort
    private Integer port;



    @BeforeEach
    void setUp() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));

        Customer customer  = new Customer("john2", "john2", "john2");
        repository.saveAndFlush(customer);
    }
    @AfterEach
    void clear() {
        repository.deleteAll();
    }

    @Test
    void shouldLoginCustomer() {
        String email = "john2";

        ResponseEntity<Customer> response = restTemplate.postForEntity(
                "/user/login",
                email,
                Customer.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Customer customer = response.getBody();
        assertNotNull(customer);
        assertEquals(email, customer.getEmail());
    }

    @Test
    void shouldRegisterCustomer() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test_email");
        registerRequest.setName("test_name");
        registerRequest.setPassword("test_password");

        ResponseEntity<Customer> response = restTemplate.postForEntity(
                "/user/register",
                registerRequest,
                Customer.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Customer customer = response.getBody();
        assertNotNull(customer);
        assertEquals("test_email", customer.getEmail());
        assertEquals("test_name", customer.getName());
        assertEquals("test_password", customer.getPassword());
    }
}
