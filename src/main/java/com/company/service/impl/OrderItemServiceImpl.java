package com.company.service.impl;

import com.company.data.entity.OrderItem;
import com.company.data.repository.OrderItemRep;
import com.company.data.repository.OrdersRep;
import com.company.service.OrderItemService;
import com.company.service.dto.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderItemService")
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private static final Logger log = LogManager.getLogger(OrderItemService.class);
    private final ObjectMapperSC mapper;
    private final OrderItemRep orderItemRep;
    private final OrdersRep ordersRep;

    @Override
    public List<OrderItemDTO> findAll() {
        log.debug("Start OrdersItemsService - findAllOrderItem");
        List<OrderItem> ordersItems = orderItemRep.findAll();
        if (ordersItems == null) {
            log.error("OrdersItemsService - findAll - OrderItem is not exist");
            throw new RuntimeException("FindAll - OrderItem is not exist...");
        }
        return ordersItems.stream().map(mapper::toOrdersItemsDTO).toList();
    }

    @Override
    public OrderItemDTO findById(Long id) {
        log.info("Start OrdersItemsService - findById - {}", id);
        OrderItemDTO orderItemDTO = mapper.toOrdersItemsDTO(orderItemRep.findById(id));
        if (orderItemDTO == null) {
            log.error("OrdersItemsService - findById - OrderItem is not exist");
            throw new RuntimeException("FindById - OrderItem is not exist...");
        }
        return orderItemDTO;
    }

    @Override
    public List<OrderItemDTO> findByOrdersId(Long order_id) {
        log.info("Start OrdersItemsService - findByOrdersId - {}", order_id);
        List<OrderItem> ordersItems = orderItemRep.findByOrdersId(order_id);
        if (ordersItems == null) {
            log.error("OrdersItemsService - findByOrdersId - OrderItem is not exist");
            throw new RuntimeException("FindByOrdersId - OrderItem is not exist...");
        }
        return ordersItems.stream().map(mapper::toOrdersItemsDTO).toList();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrderItemDTO create(OrderItemDTO orderItemDTO) {
        log.debug("Start OrderItemService - createOrderItem {}", orderItemDTO);
        orderItemRep.create(mapper.toOrderItem(orderItemDTO));
        return null;
    }

    @Override
    public OrderItemDTO update(OrderItemDTO orderItemDTO) {
        return null;
    }
}
