package com.company.web.controller.impl;

import com.company.service.UserService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, HttpSession session) {
        session.setAttribute("refer", request.getHeader("referer"));
        return "login_form";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password, HttpSession session) {
        UserDto userDto = userService.login(login, password);
        session.setAttribute("user", userDto);
        return "redirect:" + session.getAttribute("refer");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        return "redirect:" + request.getHeader("referer");
    }
}
