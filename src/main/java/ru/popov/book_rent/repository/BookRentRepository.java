package ru.popov.book_rent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.popov.book_rent.model.BookRent;

public interface BookRentRepository extends JpaRepository<BookRent, Long> {

    @Query("SELECT br FROM BOOK_RENT br WHERE br.user.id = :user_id AND br.endRent >= current_date")
    Page<BookRent> findBookRentByUser(@Param("user_id") Long id, Pageable pageable);

    @Query("SELECT br FROM BOOK_RENT br WHERE br.user.id = :user_id AND br.endRent >= current_date AND br.book.id = :book_id")
    BookRent isThisBookRentedNow(@Param("user_id") Long userId, @Param("book_id") Long bookId);
}
