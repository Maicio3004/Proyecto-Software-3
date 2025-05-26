package com.example.book_store.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(name = "ISBN")
    private String ISBN;

    private String title;
    private String author;
    private String description;
    private Integer price;
    private Integer amount;
}
