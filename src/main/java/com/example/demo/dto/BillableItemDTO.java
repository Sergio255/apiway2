package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillableItemDTO {

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

