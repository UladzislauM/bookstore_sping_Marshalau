package com.company.data.dao.impl;

import com.company.data.dao.UserDaoJdbc;
import com.company.data.dto.UserDaoDto;
import com.company.service.entity.RoleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
@RequiredArgsConstructor
public class UserDaoJdbcImpl implements UserDaoJdbc {
    private static final String GET_ALL = """
            SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name
            FROM users u 
            JOIN role r 
            ON u.role_id = r.id
            WHERE deleted = FALSE
            """;
    private static final String GET_BY_ID = """
            SELECT u.id, u.name, u.last_name, u.email, u.password, r.role_name
            FROM users u 
            JOIN role r 
            ON u.role_id = r.id 
            WHERE u.id = :id AND deleted = FALSE
            """;
    private static final String DELETE_BY_ID = """
            UPDATE users 
            SET deleted = TRUE 
            WHERE id = :id AND deleted = FALSE
            """;
    private static final String ADD_USER = """
            INSERT INTO users (name, last_name, email, password, role_id) 
            VALUES (:name, :last_name, :email, :password, (SELECT id FROM role WHERE role_name = :role_name))
            """;
    private static final String UPDATE_BY_ID = """
            UPDATE users 
            SET name = :name, last_name = :last_name, email = :email, password = :password, role_id = (SELECT id FROM role WHERE role_name = :role_name) 
            WHERE Id = :id AND deleted = FALSE
            """;
    private static final String COUNT_USERS = """
            SELECT count(*) 
            AS total 
            FROM users
            WHERE deleted = FALSE
            """;

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public UserDaoDto findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<UserDaoDto> findAll() {
        return namedJdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public UserDaoDto create(UserDaoDto user) {
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
    public UserDaoDto update(UserDaoDto user) {
        Map<String, Object> map = new HashMap<>();
        extractedUser(user, map);
        namedJdbcTemplate.update(UPDATE_BY_ID, map);
        return findById(user.getId());
    }

    @Override
    public boolean delete(Long id) {
        int rowsUpdated = namedJdbcTemplate.update(DELETE_BY_ID, new MapSqlParameterSource("id", id));
        return rowsUpdated == 1;
    }

    @Override
    public Long countAll() {
        return namedJdbcTemplate.queryForObject(COUNT_USERS, new MapSqlParameterSource(), Long.class);
    }

    private void extractedUser(UserDaoDto user, Map<String, Object> map) {
        map.put("name", user.getName());
        map.put("last_name", user.getLast_name());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("role_name", String.valueOf(user.getRoleDaoDTO()));
        map.put("id", user.getId());
    }

    public UserDaoDto processRow(ResultSet rs, int rowNum) throws SQLException {
        UserDaoDto user = new UserDaoDto();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRoleDaoDTO(RoleUser.valueOf(rs.getString("role_name")));
        return user;
    }
}
