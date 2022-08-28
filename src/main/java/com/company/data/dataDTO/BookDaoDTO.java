package com.company.data.dataDTO;

import com.company.entity.CoverBook;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Data
public class BookDaoDTO {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private CoverBook coverBookDaoDTO;
    private BigDecimal price;
    private String isbn;
    private Boolean deleted;
}
