package com.company.dao.service.serviceImpl;

import com.company.dao.entity.User;
import com.company.dao.repository.UserDaoJdbc;
import com.company.dao.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserDaoJdbc userDaoJdbc;
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserDaoJdbc userDaoJdbc) {
        this.userDaoJdbc = userDaoJdbc;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDaoJdbc.findAll();
        log.debug("Start UserService - getAllUsers: {}", users.size());
        return users;
    }

    @Override
    public User getUserById(Long id) {
        log.debug("Start UserService - getUserById: {}", id);
        return userDaoJdbc.findById(id);
    }

//    @Override
//    public User getUserByEmail(String email) {
//        log.debug("Start UserService - getUserByEmail: {}", email);
//        return userDaoJdbc.findByEmail(email);
//    }
//
//    @Override
//    public List<User> getUsersByLastName(String lastName) {
//        log.debug("Start UserService - getUsersByLastName: {}", lastName);
//        return userDaoJdbc.findUserByLastName(lastName);
//    }

    @Override
    public void deleteUserById(Long id) {
        if (userDaoJdbc.delete(id)) {
            log.debug("Start UserService - deleteUserById: {}", id);
        } else {
            log.error("UserService - deleteUserById false: {}", id);
        }
    }

    @Override
    public User createUser(User user) {
        log.debug("Start UserService - createUser: {}", user);
        return userDaoJdbc.create(user);
    }

    @Override
    public User updateUser(User user) {
        log.debug("Start UserService - updateUserById: {}", user);
        return userDaoJdbc.update(user);
    }

//    @Override
//    public Long countAllUsers() {
//        log.debug("Start UserService - countAllUsers");
//        return userDaoJdbc.countAll();
//    }
}
