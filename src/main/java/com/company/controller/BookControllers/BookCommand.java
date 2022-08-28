package com.company.controller.BookControllers;

import com.company.DTO.BookDTO;
import com.company.controller.Command;
import com.company.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("get_book_by_id")
@RequiredArgsConstructor
public class BookCommand implements Command {
    private final BookServiceImpl bookServiceImpl;
    private BookDTO bookDTO;

    private static final Logger log = LogManager.getLogger(BookCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCommand {}", req.getParameter("id"));
        try {
            bookDTO = bookServiceImpl.findById(Long.parseLong(req.getParameter("id")));
            if (bookDTO.getId() == 0) {
                log.error("The book does not exist, BookCommand");
                req.setAttribute("errorMessage", "The book does not exist, BookCommand");
                return "error.jsp";
            } else {
                req.setAttribute("book", bookDTO);
                return "book.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookCommand {}", e);
            req.setAttribute("errorMessage", "The book does not exist, BookCommand: " + e);
            return "error.jsp";
        }
    }
}
