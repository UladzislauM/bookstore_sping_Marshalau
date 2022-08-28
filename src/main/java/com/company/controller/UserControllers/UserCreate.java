package com.company.controller.UserControllers;

import com.company.DTO.UserDTO;
import com.company.controller.Command;
import com.company.entity.RoleUser;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user_create")
public class UserCreate implements Command {
    private final UserService userService;
    private UserDTO userDTO;

    @Autowired
    public UserCreate(UserService userService, UserDTO userDTO) {
        this.userService = userService;
        this.userDTO = userDTO;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserCreate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            userDTO = addUserKeyHttpReq(req);
            if (userDTO.getName() == null) {
                req.setAttribute("errorMessage", "The user does not exist");
                log.error("The user does not exist");
                return "error.jsp";
            } else {
                userService.create(userDTO);
                req.setAttribute("user_count", userService.countAll());
                req.setAttribute("users", userService.findAll());
                return "users.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserCreate {}", e);
            req.setAttribute("errorMessage", "The user does not exist: " + e);
            return "error.jsp";
        }
    }

    private UserDTO addUserKeyHttpReq(HttpServletRequest req) {
        userDTO.setName(req.getParameter("name"));
        userDTO.setLast_name(req.getParameter("last_name"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setPassword(req.getParameter("password"));
        String roleStr = req.getParameter("role");
        userDTO.setRole(RoleUser.valueOf(roleStr));
        return userDTO;
    }
}
