package com.example.bookstore.entity;

import com.example.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")

@Getter
@Setter
public class Customer extends BaseEntity<Integer> {
    private String name;
    private String email;
    private String password;
    private int balance;

    public Customer() {
    }

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 10000;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
