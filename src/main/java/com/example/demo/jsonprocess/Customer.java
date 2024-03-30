package com.example.demo.jsonprocess;
public record Customer(
        Long invoiceId,
        String customerId,
        String customerName,
        Address address
) {
}
