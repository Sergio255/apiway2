package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "external-api", url = "https://api-prod.way2.com.br")
public interface ExternalApiClient {

    @PostMapping("/ph/billing/api/{subscriptionId}/energy-bills")
    String getEnergyBills(
            @RequestBody String requestBody,
            @RequestParam("api-version") String apiVersion,
            @RequestParam("subscriptionId") String subscriptionId,
            @RequestParam("x-way2-key") String apiKey
    );
}