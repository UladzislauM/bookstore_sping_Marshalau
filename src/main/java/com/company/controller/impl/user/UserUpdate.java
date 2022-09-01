package com.company.controller.impl.user;

import com.company.service.dto.UserDto;
import com.company.controller.Command;
import com.company.service.entity.RoleUser;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("user_update")
@RequiredArgsConstructor
public class UserUpdate implements Command {
    private static final Logger log = LogManager.getLogger(UserFindUserById.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserUpdate {}", req.getParameter("id"));
        UserDto userDTO = userService.findById(Long.parseLong(req.getParameter("id")));
        addUserKeyHttpReq(req, userDTO);
        userService.update(userDTO);
        req.setAttribute("user", userDTO);
        return "user.jsp";
    }

    private UserDto addUserKeyHttpReq(HttpServletRequest req, UserDto userDTO) {
        if (req.getParameter("name") != null) {
            userDTO.setName(req.getParameter("name"));
        }
        if (req.getParameter("last_name") != null) {
            userDTO.setLast_name(req.getParameter("last_name"));
        }
        if (req.getParameter("email") != null) {
            userDTO.setEmail(req.getParameter("email"));
        }
        if (req.getParameter("password") != null) {
            userDTO.setPassword(req.getParameter("password"));
        }
        if (req.getParameter("role") != null) {
            String roleStr = req.getParameter("role");
            userDTO.setRole(RoleUser.valueOf(roleStr));
        }
        return userDTO;
    }
}
