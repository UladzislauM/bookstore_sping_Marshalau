package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("book_delete")
@RequiredArgsConstructor
public class BookDelete implements Command {
    private static final Logger log = LogManager.getLogger(BookDelete.class);
    private final BookServiceImpl bookServiceImpl;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookDelete {}", req.getParameter("id"));
        bookServiceImpl.delete(Long.parseLong(req.getParameter("id")));
        req.setAttribute("books", bookServiceImpl.findAll());
        return "books.jsp";
    }
}
