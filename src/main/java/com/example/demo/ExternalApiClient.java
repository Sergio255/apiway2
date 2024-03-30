package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "external-api", url = "https://api-prod.way2.com.br")
public interface ExternalApiClient {

    @PostMapping(value = "/ph/billing/api/{subscriptionId}/energy-bills",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    String getEnergyBills(
            @RequestBody EnergyBillRequest requestBody,
            @RequestHeader("Accept") String accept,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("api-version") String apiVersion,
            @RequestHeader("x-way2-key") String apiKey,
            @PathVariable("subscriptionId") String subscriptionId
    );
}

