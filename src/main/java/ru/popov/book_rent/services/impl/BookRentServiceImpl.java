package ru.popov.book_rent.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.popov.book_rent.exceptions.NotEnoughMoneyException;
import ru.popov.book_rent.model.Book;
import ru.popov.book_rent.model.BookRent;
import ru.popov.book_rent.model.User;
import ru.popov.book_rent.repository.BookRentRepository;
import ru.popov.book_rent.services.BookRentService;
import ru.popov.book_rent.services.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BookRentServiceImpl extends AbstractServiceImpl<BookRent, Long, BookRentRepository> implements BookRentService {

    @Autowired
    public UserService userService;

    @Autowired
    public BookRentServiceImpl(BookRentRepository repo) {
        super(repo);
    }

    @Override
    public Page<BookRent> findBookRentByUser(User user, Pageable pageable) {
        return repo.findBookRentByUser(user.getId(), pageable);
    }

    @Override
    public boolean isThisBookRentedNow(User user, Book book) {
        return repo.isThisBookRentedNow(user.getId(), book.getId()) != null;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public BookRent rentTheBook(User user, Book book, Integer duration) throws NotEnoughMoneyException {

        LocalDate startRent = LocalDate.now();
        LocalDate endRent = startRent.plusMonths(duration);

        BigDecimal amount = book.getPrice().multiply(new BigDecimal(duration));

        if (user.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughMoneyException();
        }

        user.setBalance(user.getBalance().subtract(amount));
        userService.saveOrUpdate(user);

        BookRent bookRent = new BookRent(user, book, startRent, endRent);

        return repo.save(bookRent);
    }
}
