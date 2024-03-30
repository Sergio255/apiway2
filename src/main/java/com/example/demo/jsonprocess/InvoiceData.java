package com.example.demo.jsonprocess;

import java.util.List;

public record InvoiceData(
        Long invoiceId,
        List<InvoiceItem>items
) {
}