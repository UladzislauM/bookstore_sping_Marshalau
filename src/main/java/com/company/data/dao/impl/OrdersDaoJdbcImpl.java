package com.company.data.dao.impl;

import com.company.data.dao.OrdersDaoJdbc;
import com.company.data.dataDTO.OrdersDaoDTO;
import com.company.entity.StatusBook;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("ordersDao")
@RequiredArgsConstructor
public class OrdersDaoJdbcImpl implements OrdersDaoJdbc {
    private static final String GET_ALL = """
            SELECT o.id, o.user_id, o.total_cost, o.timestamp, s.status_name
            FROM orders o
            JOIN status s 
            ON o.status_id = s.id;
            """;
    private static final String GET_BY_ID = """
            SELECT o.id, o.user_id, o.total_cost, o.timestamp, s.status_name
            FROM orders o
            JOIN status s 
            ON o.status_id = s.id
            WHERE o.id = :id;
            """;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public OrdersDaoDTO findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<OrdersDaoDTO> findAll() {
        return namedJdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public OrdersDaoDTO create(OrdersDaoDTO ordersDTO) {
        return null;
    }

    @Override
    public OrdersDaoDTO update(OrdersDaoDTO ordersDTO) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public OrdersDaoDTO processRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrdersDaoDTO orders = new OrdersDaoDTO();
        orders.setId(resultSet.getLong("id"));
        orders.setUserId(resultSet.getLong("user_id"));
        orders.setTotalCost(resultSet.getBigDecimal("total_cost"));
        orders.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime().toLocalDate());
        orders.setStatus(StatusBook.valueOf(resultSet.getString("status_name")));
        return orders;
    }
}
