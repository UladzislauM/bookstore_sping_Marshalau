package com.company.data.repository.impl;

import com.company.data.entity.OrderItem;
import com.company.data.repository.OrderItemRep;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("orderItemRep")
@Transactional
public class OrderItemRepImpl implements OrderItemRep {
    public static final String GET_COUNT = """
            SELECT count(*)
            FROM OrderItem
            """;
    private static final String GET_ALL = """
            FROM OrderItem
            """;
    public static final String ORDER_ID = """
            FROM OrderItem oi
            WHERE oi.order.id = :order_id
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<OrderItem> findById(Long id) {
        return Optional.ofNullable(entityManager.find(OrderItem.class, id));
    }

    @Override
    public List<OrderItem> findByOrdersId(Long order_id) {
        List<OrderItem> ordersItems = entityManager.createQuery(ORDER_ID, OrderItem.class)
                .setParameter("order_id", order_id).getResultList();
        if (ordersItems == null) {
            return null;
        }
        return ordersItems;
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> ordersItems = entityManager.createQuery(GET_ALL, OrderItem.class).getResultList();
        if (ordersItems == null) {
            return null;
        }
        return ordersItems;
    }

    @Override
    public OrderItem update(OrderItem entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        entityManager.persist(orderItem);
        return orderItem;
    }
}
