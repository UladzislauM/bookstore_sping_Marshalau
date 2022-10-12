package com.company.web.interceptors;

import com.company.data.entity.RoleUser;
import com.company.data.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationInterceptor implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(RegistrationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws IOException {
        log.debug("Interceptor-PRE: {}, method: {}", req.getRequestURI(), req.getMethod());
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole() != RoleUser.ADMIN) {
                req.getSession().setAttribute("message", "Please, add permissions!");
                res.sendRedirect("/error");
                return false;
            }
        }
        return true;
    }
}
