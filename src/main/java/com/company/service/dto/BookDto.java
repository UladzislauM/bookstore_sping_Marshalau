package com.company.service.dto;

import com.company.data.entity.CoverBook;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private CoverBook coverBook;
    private BigDecimal price;
    private String isbn;
    private Boolean deleted;
}
