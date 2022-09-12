package com.company.controller.impl;

import com.company.service.BookService;
import com.company.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private static final Logger log = LogManager.getLogger(BooksController.class);
    private final BookService bookService;

    @PostMapping("/book_update_form")
    public String ToUpdatePageBook(@RequestParam Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "updateBook";
    }

    @PostMapping("/book_update/{id}")
    public String editBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/books/find_book_by_id/" + bookDto.getId();
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.create(bookDto);
        return "redirect:/books/books_find";
    }

    @GetMapping("/find_book_by_id/{id}")
    public String findBook(@PathVariable Long id, Model model) {
        log.info("Start findUser {}", model);
        model.addAttribute("book", bookService.findById(id));
        return "book";
    }

    @GetMapping("/books_find")
    public String findBooks(Model model) {
        log.info("Start findBooks {}", model);
        model.addAttribute("book_count", bookService.countAll());
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @PostMapping("/book_delete")
    public String deleteBook(@RequestParam Long id, Model model) {
        log.info("Start deleteBook {}", id);
        bookService.delete(id);
        return "redirect:/books/books_find";
    }

}
