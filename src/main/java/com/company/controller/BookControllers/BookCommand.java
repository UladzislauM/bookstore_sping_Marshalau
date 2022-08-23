package com.company.controller.BookControllers;

import com.company.controller.Command;
import com.company.entity.Book;
import com.company.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("get_book_by_id")
public class BookCommand implements Command {
    private final BookServiceImpl bookServiceImpl;
    private Book book;

    @Autowired
    public BookCommand(BookServiceImpl bookServiceImpl, Book book) {
        this.bookServiceImpl = bookServiceImpl;
        this.book = book;
    }

    private static final Logger log = LogManager.getLogger(BookCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCommand {}", req.getParameter("id"));
        try {
            book = bookServiceImpl.findById(Long.parseLong(req.getParameter("id")));
            if (book.getId() == 0) {
                log.error("The book does not exist, BookCommand");
                req.setAttribute("errorMessage", "The book does not exist, BookCommand");
                return "error.jsp";
            } else {
                req.setAttribute("book", book);
                return "book.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookCommand {}", e);
            req.setAttribute("errorMessage", "The book does not exist, BookCommand: " + e);
            return "error.jsp";
        }
    }
}
