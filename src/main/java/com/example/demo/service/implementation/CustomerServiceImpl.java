package com.example.demo.service.implementation;


import com.example.demo.entity.CustomerEntity;
import com.example.demo.repository.ICustomerRepository;
import com.example.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Implement other service methods as needed
}
