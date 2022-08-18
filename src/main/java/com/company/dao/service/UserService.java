package com.company.dao.service;

import com.company.dao.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserService {
    public List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByLastName(String lastName);

    void deleteUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    Long countAllUsers();
}
