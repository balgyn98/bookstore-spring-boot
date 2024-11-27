package com.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orderbook",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @Column(name = "opening_timestamp")
    private Date openingTimestamp;

    @Column(name = "closing_timestamp")
    private Date closingTimestamp;

    @Column(name = "total_amount")
    private int totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Order() {
    }

    public Order(Customer customer, List<Book> books, Date openingTimestamp, Date closingTimestamp, int totalAmount, Status status) {
        this.customer = customer;
        this.books = books;
        this.openingTimestamp = openingTimestamp;
        this.closingTimestamp = closingTimestamp;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getOpeningTimestamp() {
        return openingTimestamp;
    }

    public void setOpeningTimestamp(Date openingTimestamp) {
        this.openingTimestamp = openingTimestamp;
    }

    public Date getClosingTimestamp() {
        return closingTimestamp;
    }

    public void setClosingTimestamp(Date closingTimestamp) {
        this.closingTimestamp = closingTimestamp;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", books=" + books +
                ", openingTimestamp=" + openingTimestamp +
                ", closingTimestamp=" + closingTimestamp +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}
