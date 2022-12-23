package com.company.data.repository.impl;

import com.company.data.repository.UserRep;
import com.company.data.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    private static final String GET_USER_BY_LOGIN_PAS = """
            FROM User u
            WHERE u.email = :login AND u.password = :password
            """;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
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
        return entityManager.createQuery(GET_COUNT, Long.class).getSingleResult();
    }

    public boolean active(Long id, User user) {
        entityManager.merge(user);
        return user.getIs_active();
    }

    @Override
    public Optional<User> login(String login, String password) {
        return Optional.of(entityManager.createQuery(GET_USER_BY_LOGIN_PAS, User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult());
    }
}
