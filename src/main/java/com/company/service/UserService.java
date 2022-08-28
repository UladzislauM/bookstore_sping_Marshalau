package com.company.service;

import com.company.DTO.UserDTO;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends AbstractService<User, UserDTO> {
    Long countAll();
}
