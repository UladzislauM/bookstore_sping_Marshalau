package com.company.service.serviceImpl;

import com.company.entity.Orders;
import com.company.data.repository.OrdersRepJdbc;
import com.company.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepJdbc ordersRepJdbc;

    private static final Logger log = LogManager.getLogger(OrdersServiceImpl.class);

    @Override
    public List<Orders> findAll() {
        List<Orders> orders = ordersRepJdbc.findAll();
        log.info("Start OrdersServiceImpl - findAll - {}", orders.size());
        return orders;
    }

    @Override
    public Orders findById(Long id) {
        log.info("Start OrdersServiceImpl - findById - {}", id);
        return ordersRepJdbc.findById(id);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Orders create(Orders orders) {
        return null;
    }

    @Override
    public Orders update(Orders orders) {
        return null;
    }
}
