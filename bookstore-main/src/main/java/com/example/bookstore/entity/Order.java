package com.example.bookstore.entity;


import com.example.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")

@Getter
@Setter
public class Order extends BaseEntity<Integer> {
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", customer=" + customer +
                ", books=" + books +
                ", openingTimestamp=" + openingTimestamp +
                ", closingTimestamp=" + closingTimestamp +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}
