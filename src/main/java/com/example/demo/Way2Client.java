package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "way2Client", url = "${way2.url}")
public interface Way2Client {

    @PostMapping("/ph/billing/api/{subscriptionId}/energy-bills")
    String createEnergyBill(
            @RequestHeader("Accept") String accept,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("api-version") String apiVersion,
            @RequestHeader("x-way2-key") String apiKey,
            @PathVariable("subscriptionId") String subscriptionId, // This path variable is dynamic
            @RequestBody EnergyBillRequest request);
}