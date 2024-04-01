package com.example.demo.service.implementation;

import com.example.demo.entity.DistributorEntity;
import com.example.demo.repository.IDistributorRepository;
import com.example.demo.service.IDistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributorServiceImpl implements IDistributorService {
    private final IDistributorRepository distributorRepository;

    @Autowired
    public DistributorServiceImpl(IDistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    @Override
    public List<DistributorEntity> getAllDistributors() {
        return distributorRepository.findAll();
    }

    // Implement other service methods as needed
}