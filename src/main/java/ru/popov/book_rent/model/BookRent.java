package ru.popov.book_rent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "BOOK_RENT")
public class BookRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDate startRent;

    private LocalDate endRent;

    public BookRent(User user, Book book, LocalDate startRent, LocalDate endRent) {
        this.user = user;
        this.book = book;
        this.startRent = startRent;
        this.endRent = endRent;
    }

}
