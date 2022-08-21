package com.company.controller;

import com.company.ContextConfiguration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebServlet("/controller")
public class AppController extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AppController.class);
    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String commandParam = req.getParameter("post");
        log.info("Start Controller doPost {}", commandParam);
        forwardProcess(req, resp, commandParam);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String commandParam = req.getParameter("command");
        log.info("Start Controller doGet {}", commandParam);
        forwardProcess(req, resp, commandParam);
    }

    private void forwardProcess(HttpServletRequest req, HttpServletResponse resp, String commandParam) {
        try {
            if (commandParam == null) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                log.info("Address error or CommandParam == null");
            } else {
                String page = "";
                Command command = (Command) context.getBean(commandParam);
                if (command == null) {
                    log.error("Controller exception, execude {}");
                    req.setAttribute("errorMessage", "Oops..... You entered the wrong command.....");
                    page = "error.jsp";
                } else {
                    try {
                        page = command.execude(req);
                    } catch (Exception e) {
                        log.error("Controller exception, execude {}", e);
                        req.setAttribute("errorMessage", "Oops..... Page not found.....");
                        page = "error.jsp";
                    }
                }
                req.getRequestDispatcher(page).forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            log.error("Controller exception, forward {}", e);
        }
    }

    @Override
    public void destroy() {
        context.close();
    }
}
