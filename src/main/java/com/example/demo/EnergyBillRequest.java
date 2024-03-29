package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class EnergyBillRequest {
    private String referenceDateStart;
    private String referenceDateEnd;
    private List<String> billSources;

    // Constructors, getters, and setters


    public EnergyBillRequest(String referenceDateStart, String referenceDateEnd, List<String> billSources) {
        this.referenceDateStart = referenceDateStart;
        this.referenceDateEnd = referenceDateEnd;
        this.billSources = billSources;
    }

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

    public List<String> getBillSources() {
        return billSources;
    }

    public void setBillSources(List<String> billSources) {
        this.billSources = billSources;
    }
}

