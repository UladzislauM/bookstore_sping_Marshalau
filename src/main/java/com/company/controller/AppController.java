package com.company.controller;

import com.company.ContextConfiguration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start Controller doPost");
        forwardProcess(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Start Controller doGet");
        forwardProcess(req, resp);
    }

    private void forwardProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParam = req.getParameter("command");
        Command command = (Command) context.getBean(commandParam);
        String page;
        if (commandParam == null || command == null) {
            page = "JSP/index.jsp";
            log.info("Address error or Command == null");
        } else {
            try {
                page = command.execute(req);
            } catch (Exception e) {
                log.error("Controller exception, execute {}", e.getMessage(), e);
                req.setAttribute("errorMessage", "Oops..... " + e.getMessage());
                page = "JSP/error.jsp";
            }
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    public void destroy() {
        context.close();
    }
}
