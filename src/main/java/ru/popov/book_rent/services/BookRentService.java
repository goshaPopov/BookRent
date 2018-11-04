package ru.popov.book_rent.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.popov.book_rent.exceptions.NotEnoughMoneyException;
import ru.popov.book_rent.model.Book;
import ru.popov.book_rent.model.BookRent;
import ru.popov.book_rent.model.User;
import ru.popov.book_rent.services.impl.AbstractService;

public interface BookRentService extends AbstractService<BookRent, Long> {

    Page<BookRent> findBookRentByUser(User user, Pageable pageable);

    BookRent rentTheBook(User user, Book book, Integer duration) throws NotEnoughMoneyException;

    boolean isThisBookRentedNow(User user, Book book);

}
