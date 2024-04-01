package com.example.demo.service.implementation;

import com.example.demo.entity.InvoiceItemEntity;
import com.example.demo.repository.IInvoiceItemRepository;
import com.example.demo.service.IInvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImpl implements IInvoiceItemService {
    private final IInvoiceItemRepository invoiceItemRepository;

    @Autowired
    public InvoiceItemServiceImpl(IInvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public InvoiceItemEntity create(InvoiceItemEntity invoiceItemEntity) {
        return invoiceItemRepository.save(invoiceItemEntity);
    }

    @Override
    public List<InvoiceItemEntity> getAllInvoiceItems() {
        return invoiceItemRepository.findAll();
    }
}

