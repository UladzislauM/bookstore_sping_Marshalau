package com.company.data.dto;

import com.company.service.entity.StatusBook;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Data
public class OrdersDaoDto {
    private Long id;
    private Long userId;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
}
