package com.company.repository.impl;

import com.company.entity.OrdersItems;
import com.company.repository.OrderItemDaoJdbc;
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
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final BookDaoJdbcImpl bookDaoJdbc;

    @Override
    public OrdersItems findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject("SELECT * FROM orders_items WHERE id = :id",
                    new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<OrdersItems> findAll() {
        return null;
    }

    @Override
    public OrdersItems create(OrdersItems entity, Long orderId) {
        return null;
    }

    @Override
    public OrdersItems update(OrdersItems entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public OrdersItems processRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrdersItems ordersItems = new OrdersItems();
        ordersItems.setId(resultSet.getLong("id"));
        ordersItems.setPrice(resultSet.getBigDecimal("price"));
        ordersItems.setQuantity(resultSet.getInt("quantity"));
        Long bookId = resultSet.getLong("book_id");
        ordersItems.setBook(bookDaoJdbc.findById(bookId));
        return ordersItems;
    }
}
