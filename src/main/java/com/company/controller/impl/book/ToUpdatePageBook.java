package com.company.controller.impl.book;

import com.company.controller.Command;
import com.company.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("book_update_form")
@RequiredArgsConstructor
public class ToUpdatePageBook implements Command {
    private static final Logger log = LogManager.getLogger(BookDelete.class);
    private final BookServiceImpl bookServiceImpl;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start ToUpdatePageBook {}", req.getParameter("id"));
        req.setAttribute("book", bookServiceImpl.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/updateBook.jsp";
    }
}
