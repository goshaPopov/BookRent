package ru.popov.book_rent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.popov.book_rent.model.Book;
import ru.popov.book_rent.model.BookRent;

import java.io.Serializable;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRentResponse implements Serializable {

    private Long id;

    private Book book;

    private Integer timeToEnd;

    public BookRentResponse(BookRent bookRent) {
        this.id = bookRent.getId();
        this.book = bookRent.getBook();
        this.timeToEnd = Period.between(bookRent.getStartRent(), bookRent.getEndRent()).getMonths();
    }

}
