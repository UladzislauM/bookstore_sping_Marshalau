package com.company.service.impl;

import com.company.service.EncryptionService;
import com.company.service.dto.UserDto;
import com.company.data.repository.UserRep;
import com.company.data.entity.User;
import com.company.service.UserService;
import com.company.service.exception.ResourceNotFoundException;
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
    private final EncryptionService encryptionService;

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
        User user = userRepJdbc.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("User with id: " + id + " wasn't found");
        });
        UserDto userDTO = mapper.toUserDTO(user);
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
    public UserDto create(UserDto userDTO) {
        log.debug("Start UserService - createUser: {}", userDTO);
        User user = mapper.toUser(userDTO);
        if(user == null){
            log.error("UserService - create false:");
            throw new RuntimeException("CreateUser false...");
        }
        String originalPassword = user.getPassword();
        String hashedPassword = encryptionService.digest(originalPassword);
        user.setPassword(hashedPassword);
        return mapper.toUserDTO(userRepJdbc.create(user));
    }

    @Override
    public UserDto update(UserDto userDTO) {
        log.debug("Start UserService - updateUserById: {}", userDTO);
        User user = mapper.toUser(userDTO);
        if(user == null){
            log.error("UserService - update false:");
            throw new RuntimeException("UpdateUser false...");
        }
        return mapper.toUserDTO(userRepJdbc.update(user));
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

    @Override
    public UserDto login(String login, String password) {
        String hashedPassword = encryptionService.digest(password);
        return mapper.toUserDTO(userRepJdbc.login(login, hashedPassword).orElseThrow(() -> {
            throw new ResourceNotFoundException("User with login: " + login + " wasn't found");
        }));
    }
}
