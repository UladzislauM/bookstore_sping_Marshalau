package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.entity.User;
import com.company.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("get_user_by_id")
public class UserCommand implements Command {
    private final UserServiceImpl userServiceImpl;
    private User user;

    @Autowired
    public UserCommand(UserServiceImpl userServiceImpl, User user) {
        this.userServiceImpl = userServiceImpl;
        this.user = user;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserCommand {}", req.getParameter("id"));
        try {
            user = userServiceImpl.getUserById(Long.parseLong(req.getParameter("id")));
            if (user.getId() == null) {
                req.setAttribute("errorMessage", "The user does not exist");
                log.error("The user does not exist");
                return "error.jsp";
            } else {
                req.setAttribute("user", user);
                return "user.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by UserCommand {}", e);
            req.setAttribute("errorMessage", "The user does not exist: " + e);
            return "error.jsp";
        }
    }
}
