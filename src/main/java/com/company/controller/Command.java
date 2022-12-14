package com.company.controller;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface Command {
    String execute(HttpServletRequest req);
}
