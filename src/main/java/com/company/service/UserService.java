package com.company.service;

import com.company.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends AbstractService<UserDto> {
    Long countAll();

    void active(Long id, boolean user_status);

    UserDto login(String login, String password);
}
