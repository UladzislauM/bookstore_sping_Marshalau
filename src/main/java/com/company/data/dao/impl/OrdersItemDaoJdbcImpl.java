package com.company.data.dao.impl;

import com.company.data.dao.OrderItemDaoJdbc;
import com.company.data.dataDTO.OrdersItemsDaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersItemDaoJdbcImpl implements OrderItemDaoJdbc {
    public static final String SELECT_FROM_ID = """
            SELECT oi.id, oi.orders_id, oi.book_id, oi.quantity, oi.price
            FROM orders_items oi
            WHERE oi.id = :id
            """;
    public static final String SELECT_FROM_ORDER_ID = """
            SELECT oi.id, oi.orders_id, oi.book_id, oi.quantity, oi.price
            FROM orders_items oi
            WHERE oi.orders_id = :orders_id
            """;
    private static final String GET_ALL = """
            SELECT oi.id, oi.orders_id, oi.book_id, oi.quantity, oi.price
            FROM orders_items oi
            """;

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public OrdersItemsDaoDTO findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject(SELECT_FROM_ID,
                    new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<OrdersItemsDaoDTO> findAll() {
        return namedJdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public List<OrdersItemsDaoDTO> findByOrderId(Long order_id) {
        List<OrdersItemsDaoDTO> orderItems = null;
        try {
            orderItems = namedJdbcTemplate.query(SELECT_FROM_ORDER_ID,
                    new MapSqlParameterSource("orders_id", order_id), this::processRow);
            return orderItems;
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public OrdersItemsDaoDTO create(OrdersItemsDaoDTO entity, Long orderId) {
        return null;
    }

    @Override
    public OrdersItemsDaoDTO update(OrdersItemsDaoDTO entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public OrdersItemsDaoDTO processRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrdersItemsDaoDTO ordersItemsDTO = new OrdersItemsDaoDTO();
        ordersItemsDTO.setId(resultSet.getLong("id"));
        ordersItemsDTO.setOrder_id(resultSet.getLong("orders_id"));
        ordersItemsDTO.setBook_id(resultSet.getLong("book_id"));
        ordersItemsDTO.setPrice(resultSet.getBigDecimal("price"));
        ordersItemsDTO.setQuantity(resultSet.getInt("quantity"));
        return ordersItemsDTO;
    }
}
