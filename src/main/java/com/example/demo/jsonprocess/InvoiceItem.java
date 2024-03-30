package com.example.demo.jsonprocess;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record InvoiceItem(
        Long invoiceId,
        String sdpId,
        String uc,
        String site,
        String billSource,
        String suppliedVoltageType,
        String billCalculationTrigger,
        String tariffType,
        String billClass,
        LocalDateTime insertedAt,
        LocalDateTime referenceDate,
        LocalDateTime readingBegin,
        LocalDateTime readingEnd,
        LocalDateTime readingDate,
        LocalDateTime previousReading,
        LocalDateTime nextReading,
        LocalDateTime dueDate,
        List<BillableItem> billableItems,
        List<TaxItem> taxItems,
        List<OtherItem> otherItems,
        List<QualityItem> qualityItems,
        boolean hasCustomData,
        BigDecimal totalAmount,
        BigDecimal totalExceptTaxes,
        BigDecimal taxes,
        BigDecimal others,
        boolean validated,
        int numberOfFiles,
        int alerts,
        int totalDays,
        BigDecimal readingBeginKwh,
        BigDecimal readingEndKwh,
        BigDecimal constant,
        String barcode,
        BigDecimal subTotalAmount,
        String contractNumber,
        String installationId,
        String className,
        String subClass,
        String invoiceNumber,
        String invoiceSeries,
        LocalDateTime issueDate,
        int billingDays,
        String automaticDebitCode,
        String meterId,
        String monthDelay,
        String valueDelay,
        String tensionGroup,
        String fareSubGroup,
        String annotations,
        Customer customer,
        Distributor distributor,
        String subscriptionId
) {}