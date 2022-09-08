package com.company.service.impl;

import com.company.service.OrdersItemsService;
import com.company.service.dto.OrdersDto;
import com.company.data.entity.Orders;
import com.company.data.repository.OrdersRep;
import com.company.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("orderService")
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private static final Logger log = LogManager.getLogger(OrdersServiceImpl.class);
    private final OrdersRep ordersRepJdbc;
    private final ObjectMapperSC mapper;
    private final OrdersItemsService ordersItemsService;

    @Override
    public List<OrdersDto> findAll() {
        log.info("Start OrdersService - findAll");
        List<Orders> orders = ordersRepJdbc.findAll();
        if (orders == null) {
            log.error("OrdersService - findAll - Orders is not exist");
            throw new RuntimeException("FindAll - Orders is not exist...");
        } else {
            return orders.stream().map(mapper::toOrdersDTO).toList();
        }
    }

    @Override
    public OrdersDto findById(Long id) {
        log.info("Start OrdersService - findById - {}", id);
        OrdersDto ordersDTO = mapper.toOrdersDTO(ordersRepJdbc.findById(id));
        if (ordersDTO == null) {
            log.error("OrdersService - findById - Order is not exist");
            throw new RuntimeException("FindById - Order is not exist...");
        }
        List<BigDecimal> cost = new ArrayList<>();
        ordersItemsService.findByOrdersId(id).forEach(order -> {
            cost.add(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        });
        BigDecimal cost_total = cost.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        ordersDTO.setTotalCost(cost_total);
        return ordersDTO;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Orders create(OrdersDto ordersDTO) {
        return null;
    }

    @Override
    public Orders update(OrdersDto ordersDTO) {
        return null;
    }
}
