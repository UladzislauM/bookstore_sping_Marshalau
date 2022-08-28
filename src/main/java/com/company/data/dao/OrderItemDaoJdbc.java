package com.company.data.dao;

import com.company.data.dataDTO.OrdersItemsDaoDTO;
import com.company.entity.OrdersItems;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemDaoJdbc extends AbstractDaoJdbc<OrdersItemsDaoDTO> {
    OrdersItemsDaoDTO create(OrdersItemsDaoDTO entity, Long id);
    List<OrdersItemsDaoDTO> findByOrderId(Long order_id);
}
