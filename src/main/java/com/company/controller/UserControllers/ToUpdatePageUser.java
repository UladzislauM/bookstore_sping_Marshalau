package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.entity.User;
import com.company.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("user_update_form")
public class ToUpdatePageUser implements Command {
    private final UserServiceImpl userServiceImpl;
    private User user;

    @Autowired
    public ToUpdatePageUser(UserServiceImpl userServiceImpl, User user) {
        this.userServiceImpl = userServiceImpl;
        this.user = user;
    }

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start ToUpdatePageUser {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            userServiceImpl.updateUser(user);
            req.setAttribute("user", userServiceImpl.getUserById(Long.parseLong(req.getParameter("id"))));
            return "updateUser.jsp";
        } catch (Exception e) {
            log.error("Exception by ToUpdatePageUser {}", e);
            req.setAttribute("errorMessage", "The user update fail: " + e);
            return "error.jsp";
        }
    }
}
