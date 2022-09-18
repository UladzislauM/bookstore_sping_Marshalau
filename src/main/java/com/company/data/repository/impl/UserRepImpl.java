package com.company.data.repository.impl;

import com.company.data.repository.UserRep;
import com.company.data.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("userRep")
@Transactional
public class UserRepImpl implements UserRep {
    public static final String GET_COUNT = """
            SELECT count(*)
            FROM User u
            WHERE u.is_active = true
            """;
    private static final String GET_ALL = """
            FROM User
            """;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery(GET_ALL, User.class).getResultList();
        if (users == null) {
            return null;
        }
        return users;
    }

    @Override
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Long countAll() {
        Long count = entityManager.createQuery(GET_COUNT, Long.class).getSingleResult();
        return count;
    }

    public boolean active(Long id, User user) {
        entityManager.merge(user);
        return user.getIs_active();
    }
}
