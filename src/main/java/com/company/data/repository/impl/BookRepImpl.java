package com.company.data.repository.impl;

import com.company.data.entity.Book;
import com.company.data.repository.BookRep;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("bookRep")
@Transactional
public class BookRepImpl implements BookRep {
    public static final String GET_COUNT = """
            SELECT count(*)
            FROM Book
            WHERE deleted = false
            """;
    public static final String GET_COUNT_AUTHORS = """
            SELECT count(distinct name_Author)
            FROM Book
            WHERE deleted = false
            """;
    private static final String GET_ALL = """
            FROM Book
            WHERE deleted = false
            """;
    public static final String DELETE_BOOK = """
            UPDATE Book
            SET deleted = true
            WHERE id = :id
            """;
    public static final String AUTHOR = """
            FROM Book
            WHERE nameAuthor = :nameAuthor
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findByAuthor(String nameAuthor) {
        return entityManager.createQuery(AUTHOR, Book.class)
                .setParameter("nameAuthor", nameAuthor)
                .getResultList();
    }

    @Override
    public List<Book> findAll() {
        List<Book> book = entityManager.createQuery(GET_ALL, Book.class)
                .getResultList();
        if (book == null) {
            return null;
        }
        return book;
    }

    @Override
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        entityManager.merge(book);
        return book;
    }

    @Override
    public boolean delete(Long id) {
        Query query = entityManager.createQuery(DELETE_BOOK);
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery(GET_COUNT, Long.class)
                .getSingleResult();
    }

    @Override
    public Long countAllAuthors() {
        return entityManager.createQuery(GET_COUNT_AUTHORS, Long.class)
                .getSingleResult();
    }
}
