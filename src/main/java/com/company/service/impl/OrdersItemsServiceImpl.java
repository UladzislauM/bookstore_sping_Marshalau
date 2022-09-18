package com.company.service.impl;

import com.company.data.entity.Orders;
import com.company.data.entity.OrdersItems;
import com.company.data.entity.StatusBook;
import com.company.data.repository.OrderItemRep;
import com.company.data.repository.OrdersRep;
import com.company.service.OrdersItemsService;
import com.company.service.dto.BookDto;
import com.company.service.dto.OrdersDto;
import com.company.service.dto.OrdersItemsDTO;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Service("orderItemService")
@RequiredArgsConstructor
public class OrdersItemsServiceImpl implements OrdersItemsService {
    private static final Logger log = LogManager.getLogger(OrdersItemsService.class);
    private final ObjectMapperSC mapper;
    private final OrderItemRep orderItemRep;
    private final OrdersRep ordersRep;

    @Override
    public List<OrdersItemsDTO> findAll() {
        log.debug("Start OrdersItemsService - findAllOrderItem");
        List<OrdersItems> ordersItems = orderItemRep.findAll();
        if (ordersItems == null) {
            log.error("OrdersItemsService - findAll - OrderItem is not exist");
            throw new RuntimeException("FindAll - OrderItem is not exist...");
        }
            return ordersItems.stream().map(mapper::toOrdersItemsDTO).toList();
    }

    @Override
    public OrdersItemsDTO findById(Long id) {
        log.info("Start OrdersItemsService - findById - {}", id);
        OrdersItemsDTO ordersItemsDTO = mapper.toOrdersItemsDTO(orderItemRep.findById(id));
        if (ordersItemsDTO == null) {
            log.error("OrdersItemsService - findById - OrderItem is not exist");
            throw new RuntimeException("FindById - OrderItem is not exist...");
        }
        return ordersItemsDTO;
    }

    @Override
    public List<OrdersItemsDTO> findByOrdersId(Long order_id) {
        log.info("Start OrdersItemsService - findByOrdersId - {}", order_id);
        List<OrdersItems> ordersItems = orderItemRep.findByOrdersId(order_id);
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
    public OrdersItemsDTO create(OrdersItemsDTO ordersItemsDTO) {
        return null;
    }

    @Override
    public OrdersItemsDTO create(OrdersDto ordersDto) {
        log.debug("Start OrderService - createOrderItem {}", ordersDto);
        OrdersItemsDTO ordersItemsDTO = new OrdersItemsDTO();
        //Fixme
//        UserDto userDto = (UserDto) session.getAttribute("user");
//        ordersItemsDTO.setPrice(bookDto.getPrice());
//        ordersItemsDTO.setBookDto(bookDto);
//        ordersItemsDTO.setQuantity(1);
//        OrdersItems orderItem = mapper.toOrdersItems(ordersItemsDTO);
//        if (orderItem == null) {
//            log.error("OrderItemService - create false:");
//            throw new RuntimeException("CreateOrderItem false...");
//        }
//        Orders orders;
//        if(ordersItemsDTO.getOrdersDto() == null) {
//            orders = newOrder(ordersItemsDTO, userDto);
//            ordersRep.create(orders);
//        }else {
//            orders = orderItem.getOrders();
//            orders.setTotalCost(orders.getTotalCost().multiply(orderItem.getPrice()));
//            ordersRep.update(orders);
//        }
//        orderItem.setOrders(orders);
//        orderItem.setPrice(bookDto.getPrice());
//        return orderItem;
        return null;
    }

    @Override
    public OrdersItemsDTO update(OrdersItemsDTO ordersItemsDTO) {
        return null;
    }
}
