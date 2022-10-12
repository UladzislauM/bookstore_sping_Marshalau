package com.company.service;

import com.company.service.dto.OrderDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Component
public interface OrderService extends AbstractService<OrderDto> {
    OrderDto create(HttpSession session, Map<Long, Integer> cartMap);
    List<OrderDto> findByUserId(Long id);
    }
