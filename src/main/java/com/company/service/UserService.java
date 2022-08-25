package com.company.service;

import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends AbstractService<User> {
    Long countAll();
}
