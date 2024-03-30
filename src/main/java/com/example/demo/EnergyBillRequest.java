package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyBillRequest {

    private String subscriptionId; // Add subscriptionId field

    private String referenceDateStart;
    private String referenceDateEnd;
    private List<String> billSources;

}