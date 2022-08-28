package com.company.data.repository.impl;

import com.company.data.dao.impl.UserDaoJdbcImpl;
import com.company.data.dataDTO.UserDaoDTO;
import com.company.data.repository.UserRepJdbc;
import com.company.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userRep")
@RequiredArgsConstructor
public class UserRepJdbcImpl implements UserRepJdbc {
    private final UserDaoJdbcImpl userDaoJdbc;
    private final ObjectMapperDao mapper;

    @Override
    public User findById(Long id) {
        UserDaoDTO userDTO = userDaoJdbc.findById(id);
        if (userDTO != null) {
            return mapper.toUser(userDTO);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<UserDaoDTO> usersDTO = userDaoJdbc.findAll();
        List<User> users = new ArrayList<>();
        if (usersDTO != null) {
            usersDTO.stream().forEach(usersDaoDTO -> users.add(mapper.toUser(usersDaoDTO)));
            return users;
        }
        return null;
    }

    @Override
    public User create(User user) {
        if (user != null) {
            return mapper.toUser(userDaoJdbc.create(mapper.toUserDaoDTO(user)));
        }
        return null;
    }

    @Override
    public User update(User user) {
        if (user != null) {
            return mapper.toUser(userDaoJdbc.update(mapper.toUserDaoDTO(user)));
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        UserDaoDTO userDTO = userDaoJdbc.findById(id);
        if (userDTO != null) {
            return userDaoJdbc.delete(id);
        }
        return false;
    }

    @Override
    public Long countAll() {
        return userDaoJdbc.countAll();
    }
}
