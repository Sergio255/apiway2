package com.example.demo.jsonprocess;

import java.math.BigDecimal;

public record QualityItem(
        Long invoiceId,
        String name,
        BigDecimal value
) {
}