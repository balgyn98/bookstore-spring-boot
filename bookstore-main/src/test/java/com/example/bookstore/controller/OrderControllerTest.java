package com.example.bookstore.controller;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Status;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.services.OrderService;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mvel2.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class OrderControllerTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }

    OrderRepository repository;
    BookRepository bookRepository;
    CustomerRepository customerRepository;
    TestRestTemplate restTemplate;
    @Autowired
    public OrderControllerTest(OrderRepository repository, BookRepository bookRepository, CustomerRepository customerRepository,  TestRestTemplate restTemplate) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    @LocalServerPort
    private Integer port;



    @BeforeEach
    void setUp() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));

        Customer customer = new Customer("john2", "john2", "john2");
        Customer customer2 = new Customer("john1", "john1", "john");
        customerRepository.saveAndFlush(customer);
        customerRepository.saveAndFlush(customer2);

        Book book1 = new Book("book1", "author1", new Date(), 1234);
        Book book2 = new Book("book2", "author2", new Date(), 1296);

        List<Book> books = Arrays.asList(book1, book2);
        bookRepository.saveAllAndFlush(books);
        Order order = new Order(customer, books, new Date(), new Date(), 2358, Status.SUCCESS);
        repository.saveAndFlush(order);
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }

    @Test
    void shouldFindAllOrders() {
        ResponseEntity<List<Order>> response = restTemplate.exchange(
                "/orders", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Order>>() {
                });
        List<Order> orders = response.getBody();
        assertEquals(1, orders.size());
        assertEquals("john2", orders.get(0).getCustomer().getName());
    }


    @Test
    void shouldSaveOrder() {
        Customer customer = customerRepository.findById(2).orElse(null);
        Book book = bookRepository.findById(1).orElse(null);
        List<Book> books = Arrays.asList(book);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomer(customer);
        orderRequest.setUserBooksList(books);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(
                "/orders",
                orderRequest,
                Boolean.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
