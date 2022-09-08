package com.company.controller.impl.book;

import com.company.data.entity.CoverBook;
import com.company.service.dto.BookDto;
import com.company.controller.Command;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller("book_update")
@RequiredArgsConstructor
public class BookUpdate implements Command {
    private static final Logger log = LogManager.getLogger(BookDelete.class);
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookUpdate {}", req.getParameter("id"));
        BookDto bookDTO = bookService.findById(Long.parseLong(req.getParameter("id")));
        addBookKeyBoard(req, bookDTO);
        bookService.update(bookDTO);
        req.setAttribute("book_count", bookService.countAll());
        req.setAttribute("book", bookDTO);
        return "JSP/book.jsp";
    }

    private BookDto addBookKeyBoard(HttpServletRequest req, BookDto bookDTO) {
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
            bookDTO.setCoverBook(CoverBook.valueOf(req.getParameter("cover_name")));
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
