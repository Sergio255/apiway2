package com.example.demo.entity;

import com.example.demo.dto.BillableItemDTO;
import com.example.demo.dto.OtherItemDTO;
import com.example.demo.dto.QualityItemDTO;
import com.example.demo.dto.TaxItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_item")
public class InvoiceItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_item_id")
    private Long invoiceItemId;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    private InvoiceEntity invoice;

    @Column(name = "sdp_id", length = 36)
    private String sdpId;

    @Column(name = "uc", length = 20)
    private String uc;

    @Column(name = "site", length = 255)
    private String site;

    @Column(name = "energy_provider", length = 255)
    private String energyProvider;

    @Column(name = "bill_source", length = 50)
    private String billSource;

    @Column(name = "supplied_voltage_type", length = 50)
    private String suppliedVoltageType;

    @Column(name = "bill_calculation_trigger", length = 50)
    private String billCalculationTrigger;

    @Column(name = "tariff_type", length = 50)
    private String tariffType;

    @Column(name = "bill_class", length = 50)
    private String billClass;

    @Column(name = "inserted_at")
    private LocalDateTime insertedAt;

    @Column(name = "reference_date")
    private LocalDateTime referenceDate;

    @Column(name = "reading_begin")
    private LocalDateTime readingBegin;

    @Column(name = "reading_end")
    private LocalDateTime readingEnd;

    @Column(name = "reading_date")
    private LocalDateTime readingDate;

    @Column(name = "previous_reading")
    private LocalDateTime previousReading;

    @Column(name = "next_reading")
    private LocalDateTime nextReading;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "billable_items", columnDefinition = "JSON")
    private List<String> billableItems;

    @Column(name = "tax_items", columnDefinition = "JSON")
    private List<String> taxItems;

    @Column(name = "other_items", columnDefinition = "JSON")
    private List<String> otherItems;

    @Column(name = "quality_items", columnDefinition = "JSON")
    private List<String> qualityItems;

    @Column(name = "has_custom_data")
    private Boolean hasCustomData;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "total_except_taxes")
    private BigDecimal totalExceptTaxes;

    @Column(name = "taxes")
    private BigDecimal taxes;

    @Column(name = "others")
    private BigDecimal others;

    @Column(name = "validated")
    private Boolean validated;

    @Column(name = "number_of_files")
    private Integer numberOfFiles;

    @Column(name = "alerts")
    private Integer alerts;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name = "reading_begin_kwh")
    private BigDecimal readingBeginKwh;

    @Column(name = "reading_end_kwh")
    private BigDecimal readingEndKwh;

    @Column(name = "constant")
    private BigDecimal constant;

    @Column(name = "barcode", length = 255)
    private String barcode;

    @Column(name = "sub_total_amount")
    private BigDecimal subTotalAmount;

    @Column(name = "contract_number", length = 50)
    private String contractNumber;

    @Column(name = "installation_id", length = 50)
    private String installationId;

    @Column(name = "class", length = 50)
    private String classType;

    @Column(name = "sub_class", length = 50)
    private String subClass;

}
