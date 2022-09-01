package com.company.controller.impl.user;

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
    private static final Logger log = LogManager.getLogger(UserFindUserById.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserDelete {}", req.getParameter("id"));
        userService.delete(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user_count", userService.countAll());
        req.setAttribute("users", userService.findAll());
        return "users.jsp";
    }
}
