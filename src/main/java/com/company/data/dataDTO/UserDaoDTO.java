package com.company.data.dataDTO;

import com.company.entity.RoleUser;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDaoDTO {
    private Long id;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private RoleUser roleDaoDTO;
    private Boolean deleted;
}
