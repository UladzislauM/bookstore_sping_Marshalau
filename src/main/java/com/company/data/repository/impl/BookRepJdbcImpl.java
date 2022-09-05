package com.company.data.repository.impl;

import com.company.data.entity.Books;
import com.company.data.repository.BookRepJdbc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("bookRep")
@RequiredArgsConstructor
public class BookRepJdbcImpl implements BookRepJdbc {
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

    private final EntityManager entityManager;

    @Override
    public Books findById(Long id) {
        entityManager.getTransaction().begin();
        Books books = entityManager.find(Books.class, id);
        entityManager.getTransaction().commit();
        if (books == null) {
            return null;
        }
        return books;
    }

    @Override
    public List<Books> findAll() {
        entityManager.getTransaction().begin();
        List<Books> books = entityManager.createQuery(GET_ALL, Books.class).getResultList();
        entityManager.getTransaction().commit();
        if (books == null) {
            return null;
        }
        return books;
    }

    @Override
    public Books create(Books books) {
        entityManager.getTransaction().begin();
        entityManager.persist(books);
        entityManager.getTransaction().commit();
        return books;
    }

    @Override
    public Books update(Books books) {
        entityManager.getTransaction().begin();
        entityManager.merge(books);
        entityManager.getTransaction().commit();
        return books;
    }

    @Override
    public boolean delete(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(DELETE_BOOK);
        query.setParameter("id", id);
        boolean check = query.executeUpdate() == 1;
        entityManager.getTransaction().commit();
        return check;
    }

    @Override
    public Long countAll() {
        entityManager.getTransaction().begin();
        Long count = entityManager.createQuery(GET_COUNT, Long.class).getSingleResult();
        entityManager.getTransaction().commit();
        return count;
    }
}
