package ru.popov.book_rent.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String name;

    private BigDecimal price;

    public Book(String author, String name, BigDecimal price) {
        this.author = author;
        this.name = name;
        this.price = price;
    }


}
