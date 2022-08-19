package com.company.dao.dao;

import com.company.dao.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao extends AbstractDao<User, Long> {
    User findByEmail(String email);

    List<User> findUserByLastName(String lastName);
}
