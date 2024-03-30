package com.example.demo.jsonprocess;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TaxItem(
        Long invoiceId,
        String name,
        BigDecimal taxableValue,
        BigDecimal taxRate,
        BigDecimal value,
        boolean hasCustomData,
        LocalDateTime taxValidSince,
        String quality
) {}

