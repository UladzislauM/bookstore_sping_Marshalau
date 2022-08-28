package com.company.DTO;

import com.company.entity.StatusBook;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private StatusBook statusBook;
    private BigDecimal price;
    private String isbn;

}
