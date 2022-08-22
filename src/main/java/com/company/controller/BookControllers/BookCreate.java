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

@Controller("book_create")
public class BookCreate implements Command {
    private final BookServiceImpl bookServiceImpl;
    private Book book;

    @Autowired
    public BookCreate(BookServiceImpl bookServiceImpl, Book book) {
        this.bookServiceImpl = bookServiceImpl;
        this.book = book;
    }

    private static final Logger log = LogManager.getLogger(BookCreate.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCreate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            book = addBookKeyBoard(req);
            if (book.getTitle() == null) {
                req.setAttribute("errorMessage", "Ops..... The book does not created, BookCreate");
                log.error("The book does not created, BookCreate.");
                return "error.jsp";
            } else {
                bookServiceImpl.create(book);
                req.setAttribute("books", bookServiceImpl.findAll());
                return "books.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookCreate {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not created, BookCreate: " + e);
            return "error.jsp";
        }
    }


    private Book addBookKeyBoard(HttpServletRequest req) {
        book.setTitle(req.getParameter("title"));
        book.setNameAuthor(req.getParameter("name_author"));
        String dataNull = req.getParameter("data_purchase");
        List<String> dataArr = Arrays.asList(dataNull.split("-"));
        book.setDateReleaseBook(LocalDate.of
                (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        String statusStr = req.getParameter("status_book");
        book.setStatus(StatusBook.valueOf(statusStr));
        book.setPrice(BigDecimal.valueOf(Integer.parseInt(req.getParameter("price"))));
        book.setIsbn(req.getParameter("isbn"));
        return book;
    }
}
