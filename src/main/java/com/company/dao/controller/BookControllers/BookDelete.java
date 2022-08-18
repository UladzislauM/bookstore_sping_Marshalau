package com.company.dao.controller.BookControllers;

import com.company.dao.controller.Command;
import com.company.dao.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookDelete implements Command {
    private final BookServiceImpl bookServiceImpl;

    public BookDelete(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            bookServiceImpl.deleteBookById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("books", bookServiceImpl.getAllBooks());
            return "books.jsp";

        } catch (Exception e) {
            log.error("Exception by BookDelete {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not deleted: " + e);
            return "error.jsp";
        }
    }
}
