package com.example.bookstore.services;


import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Status;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersList() {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(order -> {
                    Order orderDTO = new Order();
                    orderDTO.setId(order.getId());
                    orderDTO.setTotalAmount(order.getTotalAmount());
                    orderDTO.setBooks(order.getBooks());
                    orderDTO.setCustomer(order.getCustomer());
                    orderDTO.setOpeningTimestamp(order.getOpeningTimestamp());
                    orderDTO.setClosingTimestamp(order.getClosingTimestamp());
                    return orderDTO;
                })
                .collect(Collectors.toList());
    }

    public boolean saveOrder(List<Book> usersBooksList, Customer customer) {
        Order order = new Order();

        order.setBooks(usersBooksList);
        order.setCustomer(customer);
        order.setTotalAmount(usersBooksList.stream().map(Book::getPrice).reduce(0, Integer::sum));
        order.setOpeningTimestamp(new Date());
        order.setClosingTimestamp(new Date());
        order.setStatus(Status.SUCCESS);

        orderRepository.save(order);

        return true;
    }
}
