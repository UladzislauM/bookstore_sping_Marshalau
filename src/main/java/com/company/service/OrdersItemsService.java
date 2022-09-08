package com.company.service;

import com.company.data.entity.OrdersItems;
import com.company.service.dto.OrdersItemsDTO;

import java.util.List;

public interface OrdersItemsService extends AbstractService<OrdersItems, OrdersItemsDTO> {
    List<OrdersItemsDTO> findByOrdersId(Long id);
}
