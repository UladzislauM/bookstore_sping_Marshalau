package com.company.controller.BookControllers;

import com.company.controller.Command;
import com.company.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("book_delete")
public class BookDelete implements Command {
    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public BookDelete(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookDelete {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            bookServiceImpl.delete(Long.parseLong(req.getParameter("id")));
            req.setAttribute("books", bookServiceImpl.findAll());
            return "books.jsp";
        } catch (Exception e) {
            log.error("Exception by BookDelete {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not deleted: " + e);
            return "error.jsp";
        }
    }
}
