package com.company.data.repository.impl;

import com.company.data.dao.BookDaoJdbc;
import com.company.data.dao.OrderItemDaoJdbc;
import com.company.data.dao.OrdersDaoJdbc;
import com.company.data.dao.UserDaoJdbc;
import com.company.data.dataDTO.BookDaoDTO;
import com.company.data.dataDTO.OrdersDaoDTO;
import com.company.data.dataDTO.UserDaoDTO;
import com.company.data.repository.OrdersRepJdbc;
import com.company.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ordersRep")
@RequiredArgsConstructor
public class OrdersRepJdbcImpl implements OrdersRepJdbc {

    private final OrdersDaoJdbc ordersDaoJdbc;
    private final OrderItemDaoJdbc orderItemDaoJdbc;
    private final UserDaoJdbc userDaoJdbc;
    private final BookDaoJdbc bookDaoJdbc;
    private final ObjectMapper mapper;

    @Override
    public Orders findById(Long id) {
        OrdersDaoDTO orderDTO = ordersDaoJdbc.findById(id);
        if (orderDTO == null) {
            return null;
        }
        Orders orders = mapper.toOrder(orderDTO);
        Long userId = orderDTO.getUserId();
        UserDaoDTO userDTO = userDaoJdbc.findById(userId);
        User user = mapper.toUser(userDTO);
        orders.setUser(user);
        List<OrdersItems> ordersItems = orderItemDaoJdbc.findByOrderId(orderDTO.getId()).stream()
                .map(dto -> {
                    OrdersItems items = mapper.toOrderItems(dto);
                    items.setOrders(orders);
                    Long bookId = dto.getBook_id();
                    BookDaoDTO bookDTO = bookDaoJdbc.findById(bookId);
                    Book book = mapper.toBook(bookDTO);
                    items.setBook(book);
                    return items;
                }).toList();
        orders.setItems(ordersItems);
        return orders;
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> allOrders = ordersDaoJdbc.findAll().stream()
                .map(dto -> {
                    Orders order = mapper.toOrder(dto);
                    order.setUser(mapper.toUser(userDaoJdbc.findById(dto.getUserId())));
                    List<OrdersItems> ordersItems = orderItemDaoJdbc.findByOrderId(dto.getId()).stream()
                            .map(itemsDto -> {
                                OrdersItems items = mapper.toOrderItems(itemsDto);
                                items.setOrders(order);
                                Long bookId = itemsDto.getBook_id();
                                BookDaoDTO bookDTO = bookDaoJdbc.findById(bookId);
                                Book book = mapper.toBook(bookDTO);
                                items.setBook(book);
                                return items;
                            }).toList();
                    order.setItems(ordersItems);
                    return order;
                }).toList();
        return allOrders;
    }

    @Override
    public Orders create(Orders orders) {
        return null;
    }

    @Override
    public Orders update(Orders orders) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

}
