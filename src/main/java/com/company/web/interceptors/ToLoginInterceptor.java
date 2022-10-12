package com.company.web.interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToLoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(RegistrationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws IOException {
        log.debug("Interceptor-PRE: {}, method: {}", req.getRequestURI(), req.getMethod());
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
