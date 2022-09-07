package com.company.data.repository.impl;

import com.company.data.entity.OrdersItems;
import com.company.data.repository.OrderItemRep;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("orderItemRep")
@RequiredArgsConstructor
public class OrderItemRepImpl implements OrderItemRep {
    public static final String GET_COUNT = """
            SELECT count(*) 
            FROM orders_items
            """;
    private static final String GET_ALL = """
            FROM OrdersItems 
            """;

    private final EntityManager entityManager;

    @Override
    public OrdersItems findById(Long id) {
        entityManager.getTransaction().begin();
        OrdersItems ordersItems = entityManager.find(OrdersItems.class, id);
        entityManager.getTransaction().commit();
        if (ordersItems == null) {
            return null;
        }
        return ordersItems;
    }

    @Override
    public List<OrdersItems> findAll() {
        entityManager.getTransaction().begin();
        List<OrdersItems> ordersItems = entityManager.createQuery(GET_ALL, OrdersItems.class).getResultList();
        entityManager.getTransaction().commit();
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
