package com.company.repository.impl;

import com.company.entity.RoleUser;
import com.company.entity.User;
import com.company.repository.UserDaoJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoJdbcImpl implements UserDaoJdbc {
    private static final String GET_ALL = "SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name" +
            " FROM users u JOIN role r ON u.role_id = r.id";
    private static final String GET_BY_ID = "SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name " +
            "FROM users u JOIN role r ON u.role_id = r.id WHERE u.id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String ADD_USER = "INSERT INTO users (name, last_name, email," +
            " password, role_id) VALUES (:name, :last_name, :email, :password, (SELECT id FROM role WHERE role_name = :role_name))";
    private static final String UPDATE_BY_ID = "UPDATE users SET name = :name, last_name = :last_name, email = :email," +
            "password = :password, role_id = (SELECT id FROM role WHERE role_name = :role_name) where Id = :id";
    private static final String COUNT_USERS = "SELECT count(*) AS total FROM users";

    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public UserDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, this::processRow, id);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        extractedUser(user, map);
        namedJdbcTemplate.update(ADD_USER, new MapSqlParameterSource().addValues(map), keyHolder, new String[]{"id"});
        Number number = keyHolder.getKey();
        if (number != null) {
            Long id = number.longValue();
            return findById(id);
        }
        return null;

    }

    @Override
    public User update(User user) {
        Map<String, Object> map = new HashMap<>();
        extractedUser(user, map);
        namedJdbcTemplate.update(UPDATE_BY_ID, map);
        return findById(user.getId());
    }


    @Override
    public boolean delete(Long id) {
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        return rowsUpdated == 1;
    }

    private void extractedUser(User user, Map<String, Object> map) {
        map.put("name", user.getName());
        map.put("last_name", user.getLast_name());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("role_name", String.valueOf(user.getRole()));
        map.put("id", user.getId());
    }

    public User processRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(RoleUser.valueOf(rs.getString("role_name")));
        return user;
    }
}
