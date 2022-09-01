package com.company.data.dao;

import com.company.data.dto.UserDaoDto;
import org.springframework.stereotype.Component;

@Component
public interface UserDaoJdbc extends AbstractDaoJdbc<UserDaoDto> {
    UserDaoDto create(UserDaoDto user);

    Long countAll();
}
