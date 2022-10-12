package com.company.data.repository.impl;

import com.company.data.entity.Cart;
import com.company.data.entity.User;
import com.company.data.repository.CartRep;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("cartRepository")
@Transactional
public class CartRepImpl implements CartRep {
    private static final Logger log = LogManager.getLogger(CartRepImpl.class);
    public static final String COUNT = """
            FROM Cart
            """;
    public static final String BY_USER = """
            FROM Cart
            WHERE users_id = :user_id
            """;
    public static final String DELETE_BOOK_INTO_CART = """
            DELETE FROM books_in_cart
            WHERE book_id = :id
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Cart> findById(Long id) {
        return null;
    }

    @Override
    public Cart findByUserId(Long id) {
        log.debug("Start CartRepImpl - findByUserId id = {}", id);
        User userC = entityManager.find(User.class, id);
        try {
            return entityManager.createQuery(BY_USER, Cart.class)
                    .setParameter("user_id", userC.getId())
                    .getSingleResult();
        }catch (NoResultException e) {
            log.debug("CartRepImpl - findByUserId - cart is null");
            return null;
        }
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart create(Cart cart) {
        log.debug("Start CartRepImpl - create id = {}", cart);
        entityManager.persist(cart);
        return cart;
    }

    @Override
    public Cart update(Cart cart) {
        log.debug("Start CartRepImpl - update id = {}", cart);
        return entityManager.merge(cart);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteBookIntoCart(Long bookId) {
        log.debug("Start CartRepImpl - deleteBookIntoCart id = {}", bookId);
        Query query = entityManager.createQuery(DELETE_BOOK_INTO_CART);
        query.setParameter("id", bookId);
        return query.executeUpdate() == 1;
    }
}
