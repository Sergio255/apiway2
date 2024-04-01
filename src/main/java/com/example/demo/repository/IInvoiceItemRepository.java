package com.example.demo.repository;

import com.example.demo.entity.InvoiceItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
    // Add custom query methods if needed
}
