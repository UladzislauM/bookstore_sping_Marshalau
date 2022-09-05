package com.company.data.repository.impl;

import com.company.data.repository.UserRepJdbc;
import com.company.data.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRep")
@RequiredArgsConstructor
public class UserRepJdbcImpl implements UserRepJdbc {
    public static final String GET_COUNT = """
            SELECT count(*) 
            FROM User u
            WHERE u.is_active = true 
            """;
    private static final String GET_ALL = """
            FROM User 
            """;
    private final EntityManager entityManager;

    @Override
    public User findById(Long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(GET_ALL, User.class).getResultList();
        entityManager.getTransaction().commit();
        if (users == null) {
            return null;
        }
        return users;
    }

    @Override
    public User create(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Long countAll() {
        entityManager.getTransaction().begin();
        Long count = entityManager.createQuery(GET_COUNT, Long.class).getSingleResult();
        entityManager.getTransaction().commit();
        return count;
    }

    public boolean active(Long id, User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user.getIs_active();
    }
}
