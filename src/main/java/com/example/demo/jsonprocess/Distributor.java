package com.example.demo.jsonprocess;

public record Distributor(
        Long invoiceId,
        String cnpjDistributor,
        String energyProvider
) {
}
