package com.company.controller.impl.user;

import com.company.controller.Command;
import com.company.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("user_update_form")
@RequiredArgsConstructor
public class ToUpdatePageUser implements Command {
    private static final Logger log = LogManager.getLogger(UserFindById.class);
    private final UserServiceImpl userServiceImpl;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start ToUpdatePageUser {}", req.getParameter("id"));
        req.setAttribute("user", userServiceImpl.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/updateUser.jsp";
    }
}
