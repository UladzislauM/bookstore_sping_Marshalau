package com.company.controller.impl.user;

import com.company.controller.Command;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("users_find")
@RequiredArgsConstructor
public class UsersFindAll implements Command {
    private static final Logger log = LogManager.getLogger(UsersFindAll.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UsersFindAll {}", req);
        req.setAttribute("user_count", userService.countAll());
        req.setAttribute("users", userService.findAll());
        return "JSP/users.jsp";
    }
}
