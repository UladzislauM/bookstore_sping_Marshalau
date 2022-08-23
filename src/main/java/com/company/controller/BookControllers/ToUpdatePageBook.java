package com.company.controller.BookControllers;

import com.company.controller.Command;
import com.company.entity.Book;
import com.company.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("book_update_form")
public class ToUpdatePageBook implements Command {
    private final BookServiceImpl bookServiceImpl;
    private Book book;

    @Autowired
    public ToUpdatePageBook(BookServiceImpl bookServiceImpl, Book book) {
        this.bookServiceImpl = bookServiceImpl;
        this.book = book;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start ToUpdatePageBook {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("book", bookServiceImpl.findById(Long.parseLong(req.getParameter("id"))));
            return "updateBook.jsp";
        } catch (Exception e) {
            log.error("Exception by ToUpdatePageBook {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not update: " + e);
            return "error.jsp";
        }
    }
}
