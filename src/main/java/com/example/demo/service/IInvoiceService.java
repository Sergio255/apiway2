package com.example.demo.service;

import com.example.demo.entity.InvoiceEntity;
import com.example.demo.entity.InvoiceItemEntity;

import java.util.List;

public interface IInvoiceService {

    InvoiceEntity save(InvoiceEntity invoiceEntity);
    List<InvoiceEntity> getAllInvoices();
    // Add other service methods as needed
}