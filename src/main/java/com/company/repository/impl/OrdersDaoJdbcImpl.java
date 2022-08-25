package com.company.repository.impl;

import com.company.entity.*;
import com.company.repository.OrdersDaoJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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
    private static final String ADD_BOOK = """
            INSERT INTO orders(o.user_id, o.total_cost, o.timestamp, o.status_id) 
            VALUES (:user_id, :total_cost, :timestamp, :status_id)
            """;//fixme

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final UserDaoJdbcImpl userDaoJdbc;

    @Override
    public Orders findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<Orders> findAll() {
        return namedJdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public Orders create(Orders orders) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        extractedOrder(orders, map);
        namedJdbcTemplate.update(ADD_BOOK, new MapSqlParameterSource().addValues(map),
                keyHolder, new String[]{"id"});
        Number number = keyHolder.getKey();
        if (number != null) {
            Long id = number.longValue();
            return findById(id);
        }
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

    private void extractedOrder(Orders orders, Map<String, Object> map) {
        map.put("total_cost", orders.getTotalCost());
        map.put("timestamp", Date.valueOf(orders.getTimestamp()));
        map.put("status_name", orders.getStatus());
        map.put("user_id", orders.getUser().getId());
    }

    public Orders processRow(ResultSet resultSet, int rowNum) throws SQLException {
        Orders orders = new Orders();
        orders.setId(resultSet.getLong("id"));
        orders.setTotalCost(resultSet.getBigDecimal("total_cost"));
        orders.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime().toLocalDate());
        orders.setStatus(StatusBook.valueOf(resultSet.getString("status_name")));
        Long userId = resultSet.getLong("user_id");
        User user = userDaoJdbc.findById(userId);//fixme
        orders.setUser(user);
        return orders;
    }
}
