package ru.popov.book_rent.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class AbstractServiceImpl<E, ID, R extends JpaRepository<E, ID>> implements AbstractService<E, ID> {

    protected R repo;

    public AbstractServiceImpl(R repo) {
        this.repo = repo;
    }

    public E saveOrUpdate(E ent) {
        return repo.save(ent);
    }

    public Optional<E> findById(ID id) {
        return repo.findById(id);
    }

    public Page<E> findAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public void delete(E ent) {
        repo.delete(ent);
    }

}
