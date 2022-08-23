package com.company.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
@Component
@Data
public class Book {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private CoverBook coverBook;
    private BigDecimal price;
    private String isbn;

}
