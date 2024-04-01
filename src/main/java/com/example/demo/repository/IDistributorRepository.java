package com.example.demo.repository;

import com.example.demo.entity.DistributorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistributorRepository extends JpaRepository<DistributorEntity, Long> {
    // Add custom query methods if needed
}
