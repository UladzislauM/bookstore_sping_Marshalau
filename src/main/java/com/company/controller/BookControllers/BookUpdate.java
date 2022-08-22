package com.company.controller.BookControllers;

import com.company.controller.Command;
import com.company.entity.Book;
import com.company.entity.StatusBook;
import com.company.service.serviceImpl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller("book_update")
public class BookUpdate implements Command {
    private final BookServiceImpl bookServiceImpl;
    private Book book;

    @Autowired
    public BookUpdate(BookServiceImpl bookServiceImpl, Book book) {
        this.bookServiceImpl = bookServiceImpl;
        this.book = book;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookUpdate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            book = bookServiceImpl.getBookById(Long.parseLong(req.getParameter("id")));
            book = addBookKeyBoard(req);
            if (book == null) {
                log.error("The book does not update, BookUpdate");
                req.setAttribute("errorMessage", "Ops..... The book does not update, BookUpdate");
                return "error.jsp";
            } else {
                bookServiceImpl.updateBook(book);
                req.setAttribute("book", book);
                return "book.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookUpdate {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not update: " + e);
            return "error.jsp";
        }
    }

    private Book addBookKeyBoard(HttpServletRequest req) {
        if (req.getParameter("title") != null) {
            book.setTitle(req.getParameter("title"));
        }
        if (req.getParameter("name_author") != null) {
            book.setNameAuthor(req.getParameter("name_author"));
        }
        if (req.getParameter("data_purchase") != null) {
            String dataNull = req.getParameter("data_purchase");
            List<String> dataArr = Arrays.asList(dataNull.split("-"));
            book.setDateReleaseBook(LocalDate.of
                    (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        }
        if (req.getParameter("status_book") != null) {
            String statusStr = req.getParameter("status_book");
            book.setStatus(StatusBook.valueOf(statusStr));
        }
        if (req.getParameter("price") != null) {
            book.setPrice(new BigDecimal(req.getParameter("price")));
        }
        if (req.getParameter("isbn") != null) {
            book.setIsbn(req.getParameter("isbn"));
        }
        return book;
    }
}
