package ru.popov.book_rent.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractService<E, ID> {

    E saveOrUpdate(E ent);

    Optional<E> findById(ID id);

    Page<E> findAll(Pageable pageable);

    void delete(E ent);

}
