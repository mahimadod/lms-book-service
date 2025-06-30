package com.example.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    private int totalCopies;
    private int availableCopies;

}
