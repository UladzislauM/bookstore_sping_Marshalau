package com.company.service.dto;

import com.company.data.entity.RoleUser;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private RoleUser role;
    private Boolean is_active;
}
