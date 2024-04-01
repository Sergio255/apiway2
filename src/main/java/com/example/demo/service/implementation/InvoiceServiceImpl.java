package com.example.demo.service.implementation;

import com.example.demo.entity.InvoiceEntity;
import com.example.demo.entity.InvoiceItemEntity;
import com.example.demo.repository.IInvoiceRepository;
import com.example.demo.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
    private final IInvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(IInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceEntity save(InvoiceEntity invoiceEntity) {
        return invoiceRepository.save(invoiceEntity);
    }

    @Override
    public List<InvoiceEntity> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Implement other service methods as needed
}

