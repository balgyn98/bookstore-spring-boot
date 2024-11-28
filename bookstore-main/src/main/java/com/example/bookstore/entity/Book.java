package com.example.bookstore.entity;


import com.example.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")

@Getter
@Setter
public class Book extends BaseEntity<Integer> {
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "published_year")
    private Date publishedYear;

    @Column(name = "price")
    private int price;

    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    private List<Order> orders;


    public Book() {
    }

    public Book(String name, String author, Date publishedYear, int price) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                '}';
    }
}
