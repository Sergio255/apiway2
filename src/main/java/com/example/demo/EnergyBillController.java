package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class EnergyBillController {

    private final RestTemplate restTemplate;

    @Autowired
    public EnergyBillController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/energy-bills")
    public ResponseEntity<String> getEnergyBills(@RequestBody String requestBody) {
        // Define headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("api-version", "2");
        headers.set("subscriptionId", "subscriptionId");
        headers.set("x-way2-key", "bb4213cbba6647cba464f660dea16550");

        // Create HttpEntity with headers and request body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make POST request to external URL
        String externalUrl = "https://api-prod.way2.com.br/ph/billing/api/abd488f6-9a38-448f-ba24-19c3a227a4f7/energy-bills";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(externalUrl, requestEntity, String.class);

        String responseBody = responseEntity.getBody();

        System.out.println(responseBody);


        // Return the response from the external URL
        return responseEntity;
    }

    public void saveJsonToFile(String json, String filePath) {
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Write JSON string to file
            FileWriter fileWriter = new FileWriter(filePath);
            objectMapper.writeValue(fileWriter, json);
            fileWriter.close();

            System.out.println("JSON data saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}