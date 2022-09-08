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

@Controller("book_create")
@RequiredArgsConstructor
public class BookCreate implements Command {
    private static final Logger log = LogManager.getLogger(BookCreate.class);
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start BookCreate {}", req.getParameter("id"));
        BookDto bookDto = addBookKeyBoard(req);
        bookService.create(bookDto);
        req.setAttribute("book_count", bookService.countAll());
        req.setAttribute("books", bookService.findAll());
        return "JSP/books.jsp";
    }


    private BookDto addBookKeyBoard(HttpServletRequest req) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(req.getParameter("title"));
        bookDto.setNameAuthor(req.getParameter("name_author"));
        String dataNull = req.getParameter("data_purchase");
        List<String> dataArr = Arrays.asList(dataNull.split("-"));
        bookDto.setDateReleaseBook(LocalDate.of
                (Integer.parseInt(dataArr.get(0)), Integer.parseInt(dataArr.get(1)), Integer.parseInt(dataArr.get(2))));
        bookDto.setCoverBook(CoverBook.valueOf(req.getParameter("cover_book")));
        bookDto.setPrice(BigDecimal.valueOf(Integer.parseInt(req.getParameter("price"))));
        bookDto.setIsbn(req.getParameter("isbn"));
        bookDto.setDeleted(Boolean.valueOf(req.getParameter("deleted")));
        return bookDto;
    }
}
