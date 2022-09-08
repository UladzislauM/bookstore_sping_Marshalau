package com.company.controller.impl.user;

import com.company.data.entity.RoleUser;
import com.company.service.dto.UserDto;
import com.company.controller.Command;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("user_create")
@RequiredArgsConstructor
public class UserCreate implements Command {
    private static final Logger log = LogManager.getLogger(UserFindById.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserCreate {}", req.getParameter("id"));
        UserDto userDTO = addUserKeyHttpReq(req);
        userService.create(userDTO);
        req.setAttribute("user_count", userService.countAll());
        req.setAttribute("users", userService.findAll());
        return "JSP/users.jsp";
    }

    private UserDto addUserKeyHttpReq(HttpServletRequest req) {
        UserDto userDTO = new UserDto();
        userDTO.setName(req.getParameter("name"));
        userDTO.setLast_name(req.getParameter("last_name"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setRole(RoleUser.valueOf(req.getParameter("role")));
        userDTO.setIs_active(Boolean.valueOf(req.getParameter("is_active")));
        return userDTO;
    }
}
