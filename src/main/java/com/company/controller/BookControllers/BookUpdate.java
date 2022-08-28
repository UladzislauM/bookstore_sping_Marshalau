package com.company.controller.BookControllers;

import com.company.DTO.BookDTO;
import com.company.controller.Command;
import com.company.entity.Book;
import com.company.entity.CoverBook;
import com.company.service.BookService;
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
    private final BookService bookService;
    private BookDTO bookDTO;

    @Autowired
    public BookUpdate(BookService bookService, BookDTO bookDTO) {
        this.bookService = bookService;
        this.bookDTO = bookDTO;
    }

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start BookUpdate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            bookDTO = bookService.findById(Long.parseLong(req.getParameter("id")));
            bookDTO = addBookKeyBoard(req);
            if (bookDTO == null) {
                log.error("The book does not update, BookUpdate");
                req.setAttribute("errorMessage", "Ops..... The book does not update, BookUpdate");
                return "error.jsp";
            } else {
                bookService.update(bookDTO);
                req.setAttribute("book", bookDTO);
                return "book.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookUpdate {}", e);
            req.setAttribute("errorMessage", "Ops..... The book does not update: " + e);
            return "error.jsp";
        }
    }

    private BookDTO addBookKeyBoard(HttpServletRequest req) {
        if (req.getParameter("title") != null) {
            bookDTO.setTitle(req.getParameter("title"));
        }
        if (req.getParameter("name_author") != null) {
            bookDTO.setNameAuthor(req.getParameter("name_author"));
        }
        if (req.getParameter("data_purchase") != null) {
            String dataNull = req.getParameter("data_purchase");
            List<String> dataArr = Arrays.asList(dataNull.split("-"));
            bookDTO.setDateReleaseBook(LocalDate.of
                    (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        }
        if (req.getParameter("cover_name") != null) {
            String coverStr = req.getParameter("cover_name");
            bookDTO.setCoverBook(CoverBook.valueOf(coverStr));
        }
        if (req.getParameter("price") != null) {
            bookDTO.setPrice(new BigDecimal(req.getParameter("price")));
        }
        if (req.getParameter("isbn") != null) {
            bookDTO.setIsbn(req.getParameter("isbn"));
        }
        return bookDTO;
    }
}
