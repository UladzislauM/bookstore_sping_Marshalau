package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("user_delete")
@RequiredArgsConstructor
public class UserDelete implements Command {
    private final UserServiceImpl userServiceImpl;

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            userServiceImpl.delete(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user_count", userServiceImpl.countAll());
            req.setAttribute("users", userServiceImpl.findAll());
            return "users.jsp";
        } catch (Exception e) {
            log.error("Exception by UserDelete {}", e);
            req.setAttribute("errorMessage", "The user does not deleted: " + e);
            return "error.jsp";
        }
    }
}
