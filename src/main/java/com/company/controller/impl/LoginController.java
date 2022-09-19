package com.company.controller.impl;

import com.company.service.CartService;
import com.company.service.UserService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpHeaders;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, HttpSession session) {
        session.setAttribute("referer", request.getHeader("referer"));
        return "login_form";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password, HttpSession session) {
        UserDto userDto = userService.login(login, password);
        session.setAttribute("user", userDto);
        String f = (String) session.getAttribute("referer");
        return "index";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
