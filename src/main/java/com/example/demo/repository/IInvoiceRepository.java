package com.example.demo.repository;

import com.example.demo.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    // Add custom query methods if needed
}
