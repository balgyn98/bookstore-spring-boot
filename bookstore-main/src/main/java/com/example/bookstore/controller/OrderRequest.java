package com.example.bookstore.controller;

import com.example.entity.Book;
import com.example.entity.Customer;

import java.util.List;

public class OrderRequest {
    private List<Book> userBooksList;
    private Customer customer;

    public List<Book> getUserBooksList() {
        return userBooksList;
    }

    public void setUserBooksList(List<Book> userBooksList) {
        this.userBooksList = userBooksList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

