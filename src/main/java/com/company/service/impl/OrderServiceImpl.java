package com.company.service.impl;

import com.company.data.entity.Book;
import com.company.data.entity.Order;
import com.company.data.entity.OrderItem;
import com.company.data.entity.StatusBook;
import com.company.data.repository.BookRep;
import com.company.data.repository.OrderItemRep;
import com.company.service.OrderItemService;
import com.company.service.dto.OrderDto;
import com.company.data.repository.OrdersRep;
import com.company.service.OrderService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrdersRep ordersRep;
    private final ObjectMapperSC mapper;
    private final OrderItemService orderItemService;
    private final OrderItemRep orderItemRep;
    private final BookRep bookRep;

    @Override
    public List<OrderDto> findAll() {
        log.info("Start OrdersService - findAll");
        List<Order> orders = ordersRep.findAll();
        if (orders == null) {
            log.error("OrdersService - findAll - Orders is not exist");
            throw new RuntimeException("FindAll - Orders is not exist...");
        } else {
            return orders.stream().map(mapper::toOrdersDTO).toList();
        }
    }

    @Override
    public OrderDto findById(Long id) {
        log.info("Start OrdersService - findById - {}", id);
        OrderDto orderDTO = mapper.toOrdersDTO(ordersRep.findById(id));
        if (orderDTO == null) {
            log.error("OrdersService - findById - Order is not exist");
            throw new RuntimeException("FindById - Order is not exist...");
        }
        List<BigDecimal> cost = new ArrayList<>();
        orderItemService.findByOrdersId(id).forEach(order -> {
            cost.add(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        });
        BigDecimal cost_total = cost.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        orderDTO.setTotalCost(cost_total);
        return orderDTO;
    }

    @Override
    public List<OrderDto>  findByUserId(Long id) {
        log.info("Start OrdersService - findByUserId - {}", id);
            List<OrderDto> orderDtoList = ordersRep.findByUserId(id).stream().map(order -> {
                return mapper.toOrdersDTO(order);
            }).toList();
        return orderDtoList;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public OrderDto create(OrderDto orderDto) {

        return null;
    }

    @Override
    public OrderDto create(HttpSession session, Map<Long, Integer> cartMap) {
        log.debug("Start OrderService - createOrder");
        return mapper.toOrdersDTO(ordersRep.update(newOrder(session, cartMap)));
    }

    private Order newOrder(HttpSession session, Map<Long, Integer> cartMap) {
        Order order = new Order();
        order.setUser(mapper.toUser((UserDto) session.getAttribute("user")));
        order.setTotalCost((BigDecimal) session.getAttribute("total_cost_cart"));
        order.setTimestamp(LocalDate.now());
        order.setStatus(StatusBook.IN_PROCESSING);
        ordersRep.create(order);
        List<OrderItem> orderItemList = new ArrayList<>();
            Iterator<Map.Entry<Long, Integer>> iterator = cartMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Integer> entry = iterator.next();
                OrderItem orderItem = createOrderItem(order, entry);
                orderItemRep.create(orderItem);
                orderItemList.add(orderItem);
            }
        order.setItems(orderItemList);
        return order;
    }

    private OrderItem createOrderItem(Order order, Map.Entry<Long, Integer> entry) {
        OrderItem orderItem = new OrderItem();
        Book book = bookRep.findById(entry.getKey());
        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setQuantity(entry.getValue());
        orderItem.setPrice(book.getPrice());
        return orderItem;
    }

    @Override
    public OrderDto update(OrderDto orderDTO) {
        return null;
    }
}
