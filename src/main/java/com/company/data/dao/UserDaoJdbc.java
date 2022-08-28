package com.company.data.dao;

import com.company.data.dataDTO.UserDaoDTO;
import com.company.entity.Book;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDaoJdbc extends AbstractDaoJdbc<UserDaoDTO> {
    UserDaoDTO create(UserDaoDTO user);

    Long countAll();
}
