package com.company.dao.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    String execude(HttpServletRequest req);
}
