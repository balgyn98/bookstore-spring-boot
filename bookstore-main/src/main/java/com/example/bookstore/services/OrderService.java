package com.example.bookstore.services;


import com.example.entity.Book;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Status;
import com.example.repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getOrdersList() {

            List<Order> orders = orderDAO.findAll();

            List<Order> orderDTOs = orders.stream()
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
            return orderDTOs;
    }

    public boolean saveOrder(List<Book> usersBooksList, Customer customer) {
        Order order = new Order();

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = timeFormat.format(ldt);
        java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);

        order.setBooks(usersBooksList);
        order.setCustomer(customer);
        order.setTotalAmount(usersBooksList.stream().map(Book::getPrice).reduce(0, Integer::sum));
        order.setOpeningTimestamp(sqlDate);
        order.setClosingTimestamp(sqlDate);
        order.setStatus(Status.SUCCESS);

        orderDAO.save(order);

        return true;
    }
}
