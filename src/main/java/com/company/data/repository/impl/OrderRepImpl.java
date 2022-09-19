package com.company.data.repository.impl;

import com.company.data.entity.Order;
import com.company.data.repository.OrdersRep;
import com.company.service.dto.OrderDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("ordersRep")
@Transactional
public class OrderRepImpl implements OrdersRep {
    private static final String GET_ALL = """
            FROM Order
            """;
    public static final String BY_USER_ID = """
            FROM Order
            WHERE user.id = :user_id
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> order = entityManager.createQuery(BY_USER_ID, Order.class)
                .setParameter("user_id", id)
                .getResultList();
        if (order == null) {
            return null;
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> order = entityManager.createQuery(GET_ALL, Order.class).getResultList();
        if (order == null) {
            return null;
        }
        return order;
    }

    @Override
    public Order create(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        entityManager.merge(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

}
