package com.company.data.dto;

import com.company.service.entity.RoleUser;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDaoDto {
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private RoleUser roleDaoDTO;
    private Boolean deleted;
}
