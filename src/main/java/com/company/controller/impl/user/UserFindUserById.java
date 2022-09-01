package com.company.controller.impl.user;

import com.company.service.UserService;
import com.company.service.dto.UserDto;
import com.company.controller.Command;
import com.company.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("get_user_by_id")
@RequiredArgsConstructor
public class UserFindUserById implements Command {
    private static final Logger log = LogManager.getLogger(UserFindUserById.class);
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start UserCommand {}", req.getParameter("id"));
        UserDto userDTO = userService.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", userDTO);
        return "user.jsp";
    }
}
