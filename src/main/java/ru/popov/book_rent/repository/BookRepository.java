package ru.popov.book_rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popov.book_rent.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
