package com.company.data.repository;

import com.company.data.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserRep extends AbstractRep<User> {
    User create(User user);

    Long countAll();

    boolean active(Long id, User user);
}
