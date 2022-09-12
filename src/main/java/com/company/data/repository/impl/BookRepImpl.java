package com.company.data.repository.impl;

import com.company.data.entity.Books;
import com.company.data.repository.BookRep;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository("bookRep")
@Transactional
public class BookRepImpl implements BookRep {
    public static final String GET_COUNT = """
            SELECT count(*) 
            FROM Books
            WHERE deleted = false
            """;
    private static final String GET_ALL = """
            FROM Books 
            WHERE deleted = false
            """;
    public static final String DELETE_BOOK = """
            UPDATE Books 
            SET deleted = true 
            WHERE id = :id
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Books findById(Long id) {
        return entityManager.find(Books.class, id);
    }

    @Override
    public List<Books> findAll() {
        List<Books> books = entityManager.createQuery(GET_ALL, Books.class).getResultList();
        if (books == null) {
            return null;
        }
        return books;
    }

    @Override
    public Books create(Books books) {
        entityManager.persist(books);
        return books;
    }

    @Override
    public Books update(Books books) {
        entityManager.merge(books);
        return books;
    }

    @Override
    public boolean delete(Long id) {
        Query query = entityManager.createQuery(DELETE_BOOK);
        query.setParameter("id", id);
        boolean check = query.executeUpdate() == 1;
        return check;
    }

    @Override
    public Long countAll() {
        Long count = entityManager.createQuery(GET_COUNT, Long.class).getSingleResult();
        return count;
    }
}
