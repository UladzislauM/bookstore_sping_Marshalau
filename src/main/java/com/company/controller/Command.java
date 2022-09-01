package com.company.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    String execute(HttpServletRequest req);
}
