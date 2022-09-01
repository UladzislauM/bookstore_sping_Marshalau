package com.company.data.dao;

import com.company.data.dto.OrdersItemsDaoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemDaoJdbc extends AbstractDaoJdbc<OrdersItemsDaoDto> {
    OrdersItemsDaoDto create(OrdersItemsDaoDto entity, Long id);
    List<OrdersItemsDaoDto> findByOrderId(Long order_id);
}
