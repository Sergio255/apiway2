package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private String invoiceSeries;
    private LocalDateTime issueDate;
    private Integer billingDays;
    private String automaticDebitCode;
    private String meterId;
    private String monthDelay;
    private String valueDelay;
    private String tensionGroup;
    private String fareSubGroup;
    private String annotations;
    private String subscriptionId;
    private CustomerDTO customer;
    private DistributorDTO distributor;
    private List<InvoiceItemDTO> items;
}

