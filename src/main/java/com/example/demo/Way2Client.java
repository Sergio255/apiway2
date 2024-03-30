package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "way2Client")
public interface Way2Client {

    @PostMapping("/ph/billing/api/abd488f6-9a38-448f-ba24-19c3a227a4f7/energy-bills")
    ResponseEntity<String> createEnergyBill(
            @RequestBody EnergyBillRequest request);
}

//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//        headers.set("api-version", "2");
//        headers.set("subscriptionId", "subscriptionId");
//        headers.set("x-way2-key", "bb4213cbba6647cba464f660dea16550");
