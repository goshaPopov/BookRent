package ru.popov.book_rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popov.book_rent.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
