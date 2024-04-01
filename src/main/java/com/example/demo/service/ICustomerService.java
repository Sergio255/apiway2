package com.example.demo.service;

import com.example.demo.entity.CustomerEntity;
import java.util.List;

public interface ICustomerService {
    List<CustomerEntity> getAllCustomers();
    // Add other service methods as needed
}
