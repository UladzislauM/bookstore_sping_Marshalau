package com.company.data.repository.impl;

import com.company.data.entity.OrdersItems;
import com.company.data.repository.OrderItemRep;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("orderItemRep")
@Transactional
public class OrderItemRepImpl implements OrderItemRep {
    public static final String GET_COUNT = """
            SELECT count(*) 
            FROM orders_items
            """;
    private static final String GET_ALL = """
            FROM OrdersItems 
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrdersItems findById(Long id) {
        OrdersItems ordersItems = entityManager.find(OrdersItems.class, id);
        if (ordersItems == null) {
            return null;
        }
        return ordersItems;
    }

    @Override
    public List<OrdersItems> findByOrdersId(Long order_id) {
        List<OrdersItems> ordersItems = entityManager.createQuery("FROM OrdersItems oi WHERE oi.orders.id = :order_id", OrdersItems.class)
                .setParameter("order_id", order_id).getResultList();
        if (ordersItems == null) {
            return null;
        }
        return ordersItems;
    }

    @Override
    public List<OrdersItems> findAll() {
        List<OrdersItems> ordersItems = entityManager.createQuery(GET_ALL, OrdersItems.class).getResultList();
        if (ordersItems == null) {
            return null;
        }
        return ordersItems;
    }

    @Override
    public OrdersItems update(OrdersItems entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public OrdersItems create(OrdersItems entity, Long id) {
        return null;
    }
}
