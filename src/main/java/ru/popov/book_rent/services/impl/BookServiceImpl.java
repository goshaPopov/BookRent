package ru.popov.book_rent.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popov.book_rent.model.Book;
import ru.popov.book_rent.repository.BookRepository;
import ru.popov.book_rent.services.BookService;

@Service
public class BookServiceImpl extends AbstractServiceImpl<Book, Long, BookRepository> implements BookService {

    @Autowired
    public BookServiceImpl(BookRepository repo) {
        super(repo);
    }
}
