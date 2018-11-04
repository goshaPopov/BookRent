package ru.popov.book_rent.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popov.book_rent.model.User;
import ru.popov.book_rent.repository.UserRepository;
import ru.popov.book_rent.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long, UserRepository> implements UserService {


    @Autowired
    public UserServiceImpl(UserRepository repo) {
        super(repo);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repo.findByLogin(login);
    }
}
