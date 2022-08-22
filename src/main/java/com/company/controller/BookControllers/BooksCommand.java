package com.company.controller.BookControllers;

import com.company.controller.Command;
import com.company.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("books")
public class BooksCommand implements Command {
    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public BooksCommand(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    private static final Logger log = LogManager.getLogger(BooksCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BooksCommand {}", req);
        try {
            req.setAttribute("book_count", bookServiceImpl.countAll());
            req.setAttribute("books", bookServiceImpl.findAll());
            return "books.jsp";
        } catch (Exception e) {
            log.error("Exception by BooksCommand {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not exist: " + e);
            return "error.jsp";
        }
    }
}

