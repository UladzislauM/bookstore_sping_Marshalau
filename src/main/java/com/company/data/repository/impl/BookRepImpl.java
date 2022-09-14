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
    public static final String GET_COUNT_AUTHORS = """
            SELECT count(distinct name_Author)
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
    public static final String AUTHOR = "FROM Books WHERE nameAuthor = :nameAuthor";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Books findById(Long id) {
        return entityManager.find(Books.class, id);
    }

    @Override
    public List<Books> findByAuthor(String nameAuthor) {
       return entityManager.createQuery(AUTHOR, Books.class).setParameter("nameAuthor", nameAuthor).getResultList();
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
        return query.executeUpdate() == 1;
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery(GET_COUNT, Long.class).getSingleResult();
    }
    @Override
    public Long countAllAuthors() {
        return entityManager.createQuery(GET_COUNT_AUTHORS, Long.class).getSingleResult();
    }
}
