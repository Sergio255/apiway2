package com.example.demo.jsonprocess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillableItem {

    Long invoiceId;
    String area;
    String name;
    BigDecimal measured;
    BigDecimal contract;
    BigDecimal billed;
    BigDecimal value;
    String previousMeterRead;
    String currentMeterRead;
    String meterMultiplier;
    BigDecimal tariff;
    boolean hasCustomData;
    int quality;

}

