package com.company.data.dto;

import com.company.service.entity.CoverBook;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Component
public class BookDaoDto {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private CoverBook coverBookDaoDTO;
    private BigDecimal price;
    private String isbn;
    private Boolean deleted;
}
