package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDTO {
    private String sdpId;
    private String uc;
    private String site;
    private String billSource;
    private String suppliedVoltageType;
    private String billCalculationTrigger;
    private String tariffType;
    private String billClass;
    private LocalDateTime insertedAt;
    private LocalDateTime referenceDate;
    private LocalDateTime readingBegin;
    private LocalDateTime readingEnd;
    private LocalDateTime readingDate;
    private LocalDateTime previousReading;
    private LocalDateTime nextReading;
    private LocalDateTime dueDate;
    private List<BillableItemDTO> billableItems;
    private List<TaxItemDTO> taxItems;
    private List<OtherItemDTO> otherItems;
    private List<QualityItemDTO> qualityItems;
    private Boolean hasCustomData;
    private BigDecimal totalAmount;
    private BigDecimal totalExceptTaxes;
    private BigDecimal taxes;
    private BigDecimal others;
    private Boolean validated;
    private Integer numberOfFiles;
    private Integer alerts;
    private Integer totalDays;
    private BigDecimal readingBeginKwh;
    private BigDecimal readingEndKwh;
    private BigDecimal constant;
    private String barcode;
    private BigDecimal subTotalAmount;
    private String contractNumber;
    private String installationId;
    private String classType;
    private String subClass;
}




