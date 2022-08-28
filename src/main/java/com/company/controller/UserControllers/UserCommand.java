package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.entity.User;
import com.company.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("get_user_by_id")
@RequiredArgsConstructor
public class UserCommand implements Command {
    private final UserServiceImpl userServiceImpl;
    private User user;

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserCommand {}", req.getParameter("id"));
        try {
            user = userServiceImpl.findById(Long.parseLong(req.getParameter("id")));
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
