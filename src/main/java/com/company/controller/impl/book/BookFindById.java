package com.company.controller.impl.book;

import com.company.service.dto.BookDto;
import com.company.controller.Command;
import com.company.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("get_book_by_id")
@RequiredArgsConstructor
public class BookFindById implements Command {
    private static final Logger log = LogManager.getLogger(BookFindById.class);
    private final BookServiceImpl bookServiceImpl;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookCommand {}", req.getParameter("id"));
        BookDto bookDto = bookServiceImpl.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("book", bookDto);
        return "JSP/book.jsp";
    }
}
