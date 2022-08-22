package com.company.repository;

import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDaoJdbc extends AbstractDaoJdbc<User>{
}
