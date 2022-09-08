package com.company.service;

import com.company.service.dto.UserDto;
import com.company.data.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends AbstractService<User, UserDto> {
    Long countAll();

    void active(Long id, boolean user_status);
}
