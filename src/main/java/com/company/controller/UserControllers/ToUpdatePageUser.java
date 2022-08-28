package com.company.controller.UserControllers;

import com.company.controller.Command;
import com.company.service.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("user_update_form")
@RequiredArgsConstructor
public class ToUpdatePageUser implements Command {
    private final UserServiceImpl userServiceImpl;

    private static final Logger log = LogManager.getLogger(UserCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start ToUpdatePageUser {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("user", userServiceImpl.findById(Long.parseLong(req.getParameter("id"))));
            return "updateUser.jsp";
        } catch (Exception e) {
            log.error("Exception by ToUpdatePageUser {}", e);
            req.setAttribute("errorMessage", "The user update fail: " + e);
            return "error.jsp";
        }
    }
}
