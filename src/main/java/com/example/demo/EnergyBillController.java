package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EnergyBillController {

    private final Way2Config way2Config;
    @Autowired
    ExternalApiClient externalApiClient;

    public EnergyBillController(
            Way2Config way2Config,
            ExternalApiClient externalApiClient
    ) {
        this.way2Config = way2Config;
        this.externalApiClient = externalApiClient;
    }

    @PostMapping("/create-energy-bill")
    public ResponseEntity<String> getEnergyBills(
            @RequestBody EnergyBillRequest requestBody
    ) {
        // Extract subscriptionId from the requestBody
        String subscriptionId = requestBody.getSubscriptionId();

        // Set headers
        String accept = MediaType.APPLICATION_JSON_VALUE;
        String contentType = MediaType.APPLICATION_JSON_VALUE;
        String apiVersion = way2Config.getApiVersion();
        String apiKey = way2Config.getApiKey();

        // Call Feign client to make the API request
        String response = externalApiClient.getEnergyBills(
                requestBody,
                accept,
                contentType,
                apiVersion,
                apiKey,
                subscriptionId
        );

        // Return ResponseEntity with response body
        return ResponseEntity.ok(response);
    }

}