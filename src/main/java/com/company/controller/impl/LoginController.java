package com.company.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger log = LogManager.getLogger(Index.class);

    @GetMapping
    public String login() {
        log.debug("Start method Login");
        return "login";
    }
    @GetMapping("/registration")
    public String registration() {
        log.debug("Start method Registration");
        return "registration";
    }
}
