package com.company.service.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private RoleUser role;
    private Boolean deleted;
}
