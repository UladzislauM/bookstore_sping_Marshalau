package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("user_delete")
@RequiredArgsConstructor
public class UserDelete implements Command {
    private final UserService userService;

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UserDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            userService.delete(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user_count", userService.countAll());
            req.setAttribute("users", userService.findAll());
            return "users.jsp";
        } catch (Exception e) {
            log.error("Exception by UserDelete {}", e);
            req.setAttribute("errorMessage", "The user does not deleted: " + e);
            return "error.jsp";
        }
    }
}
