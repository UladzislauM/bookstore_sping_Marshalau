package com.company.service;

import com.company.service.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService extends AbstractService<OrderItemDTO> {
    List<OrderItemDTO> findByOrdersId(Long id);
}
