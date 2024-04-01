package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class InvoiceEntity  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_series", length = 10)
    private String invoiceSeries;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "billing_days")
    private Integer billingDays;

    @Column(name = "automatic_debit_code", length = 50)
    private String automaticDebitCode;

    @Column(name = "meter_id", length = 50)
    private String meterId;

    @Column(name = "month_delay", length = 50)
    private String monthDelay;

    @Column(name = "value_delay", length = 50)
    private String valueDelay;

    @Column(name = "tension_group", length = 50)
    private String tensionGroup;

    @Column(name = "fare_sub_group", length = 50)
    private String fareSubGroup;

    @Column(name = "annotations", length = 255)
    private String annotations;

    @Column(name = "subscription_id", length = 36)
    private String subscriptionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorEntity distributor;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItemEntity> items;

}
