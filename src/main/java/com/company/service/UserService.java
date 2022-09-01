package com.company.service;

import com.company.service.dto.UserDto;
import com.company.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends AbstractService<User, UserDto> {
    Long countAll();
}
