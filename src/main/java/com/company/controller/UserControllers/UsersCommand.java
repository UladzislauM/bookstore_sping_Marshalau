package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("users")
@RequiredArgsConstructor
public class UsersCommand implements Command {
    private final UserService userService;

    private static final Logger log = LogManager.getLogger(UsersCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start UsersCommand {}", req);
        try {
            req.setAttribute("user_count", userService.countAll());
            req.setAttribute("users", userService.findAll());
            return "users.jsp";
        } catch (Exception e) {
            log.error("Exception by UsersCommand {}", e);
            req.setAttribute("errorMessage", "Ops..... The user does not exist: " + e);
            return "error.jsp";
        }
    }
}
