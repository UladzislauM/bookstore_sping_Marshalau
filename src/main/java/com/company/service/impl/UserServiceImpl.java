package com.company.service.impl;

import com.company.service.dto.UserDto;
import com.company.data.repository.UserRep;
import com.company.data.entity.User;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final UserRep userRepJdbc;
    private final ObjectMapperSC mapper;

    @Override
    public List<UserDto> findAll() {
        log.debug("Start UserService - findAllUsers");
        List<User> users = userRepJdbc.findAll();
        if (users == null) {
            log.error("UserService - findAll - Users is not exist");
            throw new RuntimeException("FindAll - Users is not exist...");
        } else {
            List<UserDto> userDtoList = users.stream().map(user -> {
                return mapper.toUserDTO(user);
            }).toList();
            return userDtoList;
        }
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Start UserService - getUserById: {}", id);
        UserDto userDTO = mapper.toUserDTO(userRepJdbc.findById(id));
        if (userDTO == null) {
            log.error("UserService - findById - User is not exist");
            throw new RuntimeException("FindById - User is not exist...");
        }
        return userDTO;
    }

    @Override
    public void delete(Long id) {
        if (userRepJdbc.delete(id)) {
            log.debug("Start UserService - deleteUserById: {}", id);
        } else {
            log.error("UserService - deleteUserById false: {}", id);
            throw new RuntimeException("UserService - User is not exist...");
        }
    }

    @Override
    public User create(UserDto userDTO) {
        log.debug("Start UserService - createUser: {}", userDTO);
        User user = mapper.toUser(userDTO);
        if(user == null){
            log.error("UserService - create false:");
            throw new RuntimeException("CreateUser false...");
        }
        return userRepJdbc.create(user);
    }

    @Override
    public User update(UserDto userDTO) {
        log.debug("Start UserService - updateUserById: {}", userDTO);
        User user = mapper.toUser(userDTO);
        if(user == null){
            log.error("UserService - update false:");
            throw new RuntimeException("UpdateUser false...");
        }
        return userRepJdbc.update(user);
    }

    @Override
    public Long countAll() {
        log.debug("Start UserService - countAllUsers");
        return userRepJdbc.countAll();
    }

    public void active(Long id, boolean user_status){
        User user = mapper.toUser(findById(id));
        user.setIs_active(user_status);
        if (userRepJdbc.active(id, user)) {
            log.debug("Start UserService - ActivateUserById: {}", id);
        } else {
            log.error("UserService - DeactivateUserById false: {}", id);
        }
    }
}
