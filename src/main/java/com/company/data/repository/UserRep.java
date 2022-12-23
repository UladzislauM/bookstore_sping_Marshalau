package com.company.data.repository;

import com.company.data.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRep extends AbstractRep<User> {

    Long countAll();

    boolean active(Long id, User user);

    Optional<User> login(String login, String password);
}
