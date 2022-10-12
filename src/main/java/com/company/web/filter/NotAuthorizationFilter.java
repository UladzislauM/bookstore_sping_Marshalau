package com.company.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotAuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter( HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if(!isRequireAuthorization(req)) {
            Object user = req.getSession().getAttribute("user");
            if (user == null) {
                req.getSession().setAttribute("message", "Please, login! Or add permissions!");
                res.sendRedirect("/error");
                return;
            }
        }
        chain.doFilter(req, res);
    }

    private boolean isRequireAuthorization(HttpServletRequest req) {
        String uri = req.getRequestURI();
        return uri.equals("/login")
                || uri.equals("/users/registration")
                || uri.equals("/error")
                || uri.equals("/books/books_find")
                || uri.equals("/cart/book_to_cart")
                || uri.equals("/books/authors_find")
                || uri.equals("/")
                || uri.equals("/index")
                || uri.equals("/images/404.png")
                || uri.equals("/css/style.css")
                || uri.equals("/css/normalize.css")
                || uri.equals("/css/menuStyle.css");
    }
}
