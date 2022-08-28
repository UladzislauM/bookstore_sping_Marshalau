package com.company.service.serviceImpl;

import com.company.DTO.OrdersDTO;
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
    private final ObjectMapperSC mapper;

    private static final Logger log = LogManager.getLogger(OrdersServiceImpl.class);

    @Override
    public List<OrdersDTO> findAll() {
        List<Orders> orders = ordersRepJdbc.findAll();
        log.info("Start OrdersServiceImpl - findAll - {}", orders.size());
        List<OrdersDTO> ordersDTOList = orders.stream().map(order ->{
            return mapper.toOrdersDTO(order);
        }).toList();
        return ordersDTOList;
    }

    @Override
    public OrdersDTO findById(Long id) {
        log.info("Start OrdersServiceImpl - findById - {}", id);
        OrdersDTO ordersDTO = mapper.toOrdersDTO(ordersRepJdbc.findById(id));
        return ordersDTO;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Orders create(OrdersDTO ordersDTO) {
        return null;
    }

    @Override
    public Orders update(OrdersDTO ordersDTO) {
        return null;
    }
}
