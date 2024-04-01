package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private String customerName;
    private String publicPlace;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
}

