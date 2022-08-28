package com.company.controller.UserControllers;

import com.company.DTO.UserDTO;
import com.company.controller.Command;
import com.company.entity.RoleUser;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("user_update")
@RequiredArgsConstructor
public class UserUpdate implements Command {
    private final UserService userService;
    private UserDTO userDTO;

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserUpdate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            userDTO = userService.findById(Long.parseLong(req.getParameter("id")));
            userDTO = addUserKeyHttpReq(req);
            if (userDTO.getName() == null) {
                req.setAttribute("errorMessage", "The user update fail");
                log.error("The user update fail");
                return "error.jsp";
            } else {
                userService.update(userDTO);
                req.setAttribute("user", userDTO);
                return "user.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserUpdate {}", e);
            req.setAttribute("errorMessage", "The user update fail: " + e);
            return "error.jsp";
        }
    }

    private UserDTO addUserKeyHttpReq(HttpServletRequest req) {
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
