package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.entity.RoleUser;
import com.company.entity.User;
import com.company.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user_update")
public class UserUpdate implements Command {
    private final UserServiceImpl userServiceImpl;
    private User user;

    @Autowired
    public UserUpdate(UserServiceImpl userServiceImpl, User user) {
        this.userServiceImpl = userServiceImpl;
        this.user = user;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserUpdate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            user = userServiceImpl.getUserById(Long.parseLong(req.getParameter("id")));
            user = addUserKeyHttpReq(req);
            if (user.getName() == null) {
                req.setAttribute("errorMessage", "The user update fail");
                log.error("The user update fail");
                return "error.jsp";
            } else {
                userServiceImpl.updateUser(user);
                req.setAttribute("user", user);
                return "user.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserUpdate {}", e);
            req.setAttribute("errorMessage", "The user update fail: " + e);
            return "error.jsp";
        }
    }

    private User addUserKeyHttpReq(HttpServletRequest req) {
        if (req.getParameter("name") != null) {
            user.setName(req.getParameter("name"));
        }
        if (req.getParameter("last_name") != null) {
            user.setLast_name(req.getParameter("last_name"));
        }
        if (req.getParameter("email") != null) {
            user.setEmail(req.getParameter("email"));
        }
        if (req.getParameter("password") != null) {
            user.setPassword(req.getParameter("password"));
        }
        if (req.getParameter("role") != null) {
            String roleStr = req.getParameter("role");
            user.setRole(RoleUser.valueOf(roleStr));
        }
        return user;
    }
}
