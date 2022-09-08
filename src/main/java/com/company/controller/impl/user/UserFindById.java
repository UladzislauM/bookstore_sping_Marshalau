package com.company.controller.impl.user;

import com.company.service.UserService;
import com.company.service.dto.UserDto;
import com.company.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("get_user_by_id")
@RequiredArgsConstructor
public class UserFindById implements Command {
    private static final Logger log = LogManager.getLogger(UserFindById.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserFindById {}", req.getParameter("id"));
        UserDto userDTO = userService.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", userDTO);
        return "JSP/user.jsp";
    }
}
