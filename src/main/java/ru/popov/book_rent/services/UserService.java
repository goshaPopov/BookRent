package ru.popov.book_rent.services;

import ru.popov.book_rent.model.User;
import ru.popov.book_rent.services.impl.AbstractService;

import java.util.Optional;

public interface UserService extends AbstractService<User, Long> {

    Optional<User> findByLogin(String login);

}
