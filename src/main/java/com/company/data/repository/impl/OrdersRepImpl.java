package com.company.data.repository.impl;

import com.company.data.entity.Orders;
import com.company.data.repository.OrdersRep;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ordersRep")
@RequiredArgsConstructor
public class OrdersRepImpl implements OrdersRep {
private final EntityManager entityManager;

    private static final String GET_ALL = """
            FROM Orders 
            """;

    @Override
    public Orders findById(Long id) {
        entityManager.getTransaction().begin();
        Orders orders = entityManager.find(Orders.class, id);
        entityManager.getTransaction().commit();
        return orders;
    }

    @Override
    public List<Orders> findAll() {
        entityManager.getTransaction().begin();
        List<Orders> orders = entityManager.createQuery(GET_ALL, Orders.class).getResultList();
        entityManager.getTransaction().commit();
        if (orders == null) {
            return null;
        }
        return orders;
    }

    @Override
    public Orders create(Orders orders) {
        return null;
    }

    @Override
    public Orders update(Orders orders) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

}
