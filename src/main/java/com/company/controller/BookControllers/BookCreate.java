package com.company.controller.BookControllers;

import com.company.DTO.BookDTO;
import com.company.controller.Command;
import com.company.entity.CoverBook;
import com.company.service.BookService;
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
    private final BookService bookService;
    private BookDTO bookDTO;

    @Autowired
    public BookCreate(BookService bookService, BookDTO bookDTO) {
        this.bookService = bookService;
        this.bookDTO = bookDTO;
    }

    private static final Logger log = LogManager.getLogger(BookCreate.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookCreate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            bookDTO = addBookKeyBoard(req);
            if (bookDTO.getTitle() == null) {
                req.setAttribute("errorMessage", "Ops..... The book does not created, BookCreate");
                log.error("The book does not created, BookCreate.");
                return "error.jsp";
            } else {
                bookService.create(bookDTO);
                req.setAttribute("books", bookService.findAll());
                return "books.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookCreate {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not created, BookCreate: " + e);
            return "error.jsp";
        }
    }


    private BookDTO addBookKeyBoard(HttpServletRequest req) {
        bookDTO.setTitle(req.getParameter("title"));
        bookDTO.setNameAuthor(req.getParameter("name_author"));
        String dataNull = req.getParameter("data_purchase");
        List<String> dataArr = Arrays.asList(dataNull.split("-"));
        bookDTO.setDateReleaseBook(LocalDate.of
                (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        String coverStr = req.getParameter("cover_book");
        bookDTO.setCoverBook(CoverBook.valueOf(coverStr));
        bookDTO.setPrice(BigDecimal.valueOf(Integer.parseInt(req.getParameter("price"))));
        bookDTO.setIsbn(req.getParameter("isbn"));
        return bookDTO;
    }
}
