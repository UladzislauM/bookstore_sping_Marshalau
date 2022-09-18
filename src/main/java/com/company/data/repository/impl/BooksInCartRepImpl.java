package com.company.data.repository.impl;

import com.company.data.entity.BooksInCart;
import com.company.data.repository.BooksInCartRep;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository("booksInCartRepository")
@Transactional
public class BooksInCartRepImpl implements BooksInCartRep {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public BooksInCart findById(Long id) {
        return null;
    }

    @Override
    public List<BooksInCart> findAll() {
        return null;
    }

    @Override
    public BooksInCart create(BooksInCart booksInCart) {
        entityManager.persist(booksInCart);
        return booksInCart;
    }

    @Override
    public BooksInCart update(BooksInCart entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
