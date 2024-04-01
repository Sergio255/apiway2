package com.example.demo.service;

import com.example.demo.entity.InvoiceItemEntity;
import java.util.List;

public interface IInvoiceItemService {

    InvoiceItemEntity create(InvoiceItemEntity invoiceItemEntity);
    List<InvoiceItemEntity> getAllInvoiceItems();
    // Add other service methods as needed
}


