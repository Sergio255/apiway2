package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private Way2Client way2Client;

    @Value("${way2.subscriptionId}")
    private String subscriptionId;

    @Value("${way2.apiKey}")
    private String apiKey;

    @PostMapping("/create-energy-bill")
    public ResponseEntity<String> createEnergyBill(@RequestBody EnergyBillRequest request, @Value("${way2.subscriptionId}") String subscriptionId) {
        // Set headers (apiKey is already defined in the Feign client interface)
        String apiKey = "bb4213cbba6647cba464f660dea16550";
        String accept = MediaType.APPLICATION_JSON_VALUE;
        String contentType = MediaType.APPLICATION_JSON_VALUE;
        String apiVersion = "2";

        // Call Feign client to make the API request
        String response = way2Client.createEnergyBill(accept, contentType, apiVersion, apiKey, subscriptionId, request);

        // Return ResponseEntity with response body
        return ResponseEntity.ok(response);
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