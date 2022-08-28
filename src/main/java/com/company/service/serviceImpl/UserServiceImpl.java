package com.company.service.serviceImpl;

import com.company.DTO.UserDTO;
import com.company.data.repository.UserRepJdbc;
import com.company.entity.User;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepJdbc userRepJdbc;
    private final ObjectMapperSC mapper;

    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepJdbc.findAll();
        log.debug("Start UserService - getAllUsers: {}", users.size());
        List<UserDTO> userDTOList = users.stream().map(user -> {
            return mapper.toUserDTO(user);
        }).toList();
        return userDTOList;
    }

    @Override
    public UserDTO findById(Long id) {
        log.debug("Start UserService - getUserById: {}", id);
        UserDTO userDTO = mapper.toUserDTO(userRepJdbc.findById(id));
        return userDTO;
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
    public User create(UserDTO userDTO) {
        log.debug("Start UserService - createUser: {}", userDTO);
        User user = mapper.toUser(userDTO);
        return userRepJdbc.create(user);
    }

    @Override
    public User update(UserDTO userDTO) {
        log.debug("Start UserService - updateUserById: {}", userDTO);
        User user = mapper.toUser(userDTO);
        return userRepJdbc.update(user);
    }

    @Override
    public Long countAll() {
        log.debug("Start UserService - countAllUsers");
        return userRepJdbc.countAll();
    }
}
