package com.example.demo.jsonprocess;

import java.math.BigDecimal;

public record OtherItem(
        Long invoiceId,
        String name,
        BigDecimal value,
        boolean hasCustomData
) {}
