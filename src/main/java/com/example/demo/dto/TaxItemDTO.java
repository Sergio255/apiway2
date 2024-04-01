package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxItemDTO {
    private String name;
    private BigDecimal taxableValue;
    private BigDecimal taxRate;
    private BigDecimal value;
    private boolean hasCustomData;
    private LocalDateTime taxValidSince;
    private String quality;
}

