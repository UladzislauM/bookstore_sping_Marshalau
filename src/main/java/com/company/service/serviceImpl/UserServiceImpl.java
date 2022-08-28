package com.company.service.serviceImpl;

import com.company.data.repository.UserRepJdbc;
import com.company.entity.User;
import com.company.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepJdbc userRepJdbc;
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepJdbc userRepJdbc) {
        this.userRepJdbc = userRepJdbc;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepJdbc.findAll();
        log.debug("Start UserService - getAllUsers: {}", users.size());
        return users;
    }

    @Override
    public User findById(Long id) {
        log.debug("Start UserService - getUserById: {}", id);
        return userRepJdbc.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (userRepJdbc.delete(id)) {
            log.debug("Start UserService - deleteUserById: {}", id);
        } else {
            log.error("UserService - deleteUserById false: {}", id);
        }
    }

    @Override
    public User create(User user) {
        log.debug("Start UserService - createUser: {}", user);
        return userRepJdbc.create(user);
    }

    @Override
    public User update(User user) {
        log.debug("Start UserService - updateUserById: {}", user);
        return userRepJdbc.update(user);
    }

    @Override
    public Long countAll() {
        log.debug("Start UserService - countAllUsers");
        return userRepJdbc.countAll();
    }
}
