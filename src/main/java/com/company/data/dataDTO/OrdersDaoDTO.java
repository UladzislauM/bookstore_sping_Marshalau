package com.company.data.dataDTO;

import com.company.entity.StatusBook;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Data
public class OrdersDaoDTO {
    private Long id;
    private Long userId;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
}
