package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("books")
@RequiredArgsConstructor
public class BooksCommand implements Command {
    private static final Logger log = LogManager.getLogger(BooksCommand.class);
    private final BookServiceImpl bookServiceImpl;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BooksCommand {}", req);
        req.setAttribute("book_count", bookServiceImpl.countAll());
        req.setAttribute("books", bookServiceImpl.findAll());
        return "books.jsp";
    }
}

