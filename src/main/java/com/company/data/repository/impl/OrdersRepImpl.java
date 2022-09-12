package com.company.data.repository.impl;

import com.company.data.entity.Orders;
import com.company.data.repository.OrdersRep;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("ordersRep")
@Transactional
public class OrdersRepImpl implements OrdersRep {
    private static final String GET_ALL = """
            FROM Orders 
            """;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Orders findById(Long id) {
        Orders orders = entityManager.find(Orders.class, id);
        return orders;
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> orders = entityManager.createQuery(GET_ALL, Orders.class).getResultList();
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
