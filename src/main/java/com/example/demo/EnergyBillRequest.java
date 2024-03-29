package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class EnergyBillRequest {

    private String referenceDateStart;
    private String referenceDateEnd;
    // Add any other fields as needed

    public String[] getBillSources() {
        return billSources;
    }

    public void setBillSources(String[] billSources) {
        this.billSources = billSources;
    }

    private String[] billSources ;

    // Constructor
    public EnergyBillRequest() {
    }

    // Getters and setters
    public String getReferenceDateStart() {
        return referenceDateStart;
    }

    public void setReferenceDateStart(String referenceDateStart) {
        this.referenceDateStart = referenceDateStart;
    }

    public String getReferenceDateEnd() {
        return referenceDateEnd;
    }

    public void setReferenceDateEnd(String referenceDateEnd) {
        this.referenceDateEnd = referenceDateEnd;
    }
    // Add getters and setters for other fields as needed
}

