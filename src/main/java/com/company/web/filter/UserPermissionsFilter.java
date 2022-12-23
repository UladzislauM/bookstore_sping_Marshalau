package com.company.web.filter;

import com.company.data.entity.RoleUser;
import com.company.data.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserPermissionsFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getRole() != RoleUser.ADMIN) {
            req.getSession().setAttribute("message", "Please, add permissions!");
            res.sendRedirect("/error");
            return;
        }
        chain.doFilter(req, res);
    }
}
