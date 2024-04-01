package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityDtoMapper {

    public static InvoiceDTO convertToDto(InvoiceEntity entity) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceSeries(entity.getInvoiceSeries());
        dto.setIssueDate(entity.getIssueDate());
        dto.setBillingDays(entity.getBillingDays());
        dto.setAutomaticDebitCode(entity.getAutomaticDebitCode());
        dto.setMeterId(entity.getMeterId());
        dto.setMonthDelay(entity.getMonthDelay());
        dto.setValueDelay(entity.getValueDelay());
        dto.setTensionGroup(entity.getTensionGroup());
        dto.setFareSubGroup(entity.getFareSubGroup());
        dto.setAnnotations(entity.getAnnotations());
        dto.setSubscriptionId(entity.getSubscriptionId());
        dto.setCustomer(convertToDto(entity.getCustomer()));
        dto.setDistributor(convertToDto(entity.getDistributor()));
        dto.setItems(entity.getItems().stream().map(EntityDtoMapper::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    public static InvoiceItemDTO convertToDto(InvoiceItemEntity entity) {
        InvoiceItemDTO dto = new InvoiceItemDTO();
        dto.setSdpId(entity.getSdpId());
        dto.setUc(entity.getUc());
        dto.setSite(entity.getSite());
        dto.setBillSource(entity.getBillSource());
        dto.setSuppliedVoltageType(entity.getSuppliedVoltageType());
        dto.setBillCalculationTrigger(entity.getBillCalculationTrigger());
        dto.setTariffType(entity.getTariffType());
        dto.setBillClass(entity.getBillClass());
        dto.setInsertedAt(entity.getInsertedAt());
        dto.setReferenceDate(entity.getReferenceDate());
        dto.setReadingBegin(entity.getReadingBegin());
        dto.setReadingEnd(entity.getReadingEnd());
        dto.setReadingDate(entity.getReadingDate());
        dto.setPreviousReading(entity.getPreviousReading());
        dto.setNextReading(entity.getNextReading());
        dto.setDueDate(entity.getDueDate());
//        dto.setBillableItems(entity.getBillableItems());
//        dto.setTaxItems(entity.getTaxItems());
//        dto.setOtherItems(entity.getOtherItems());
//        dto.setQualityItems(entity.getQualityItems());
        dto.setHasCustomData(entity.getHasCustomData());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setTotalExceptTaxes(entity.getTotalExceptTaxes());
        dto.setTaxes(entity.getTaxes());
        dto.setOthers(entity.getOthers());
        dto.setValidated(entity.getValidated());
        dto.setNumberOfFiles(entity.getNumberOfFiles());
        dto.setAlerts(entity.getAlerts());
        dto.setTotalDays(entity.getTotalDays());
        dto.setReadingBeginKwh(entity.getReadingBeginKwh());
        dto.setReadingEndKwh(entity.getReadingEndKwh());
        dto.setConstant(entity.getConstant());
        dto.setBarcode(entity.getBarcode());
        dto.setSubTotalAmount(entity.getSubTotalAmount());
        dto.setContractNumber(entity.getContractNumber());
        dto.setInstallationId(entity.getInstallationId());
        dto.setClassType(entity.getClassType());
        dto.setSubClass(entity.getSubClass());
        return dto;
    }

    public static CustomerDTO convertToDto(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setPublicPlace(entity.getPublicPlace());
        dto.setNeighborhood(entity.getNeighborhood());
        dto.setZipCode(entity.getZipCode());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        return dto;
    }

    public static DistributorDTO convertToDto(DistributorEntity entity) {
        DistributorDTO dto = new DistributorDTO();
        dto.setDistributorIdValue(entity.getDistributorIdValue());
        dto.setEnergyProvider(entity.getEnergyProvider());
        return dto;
    }

    public static InvoiceEntity convertToEntity(InvoiceDTO dto, CustomerEntity savedCustomer, DistributorEntity distributorSaved) {
        InvoiceEntity entity = new InvoiceEntity();
        entity.setInvoiceSeries(dto.getInvoiceSeries());
        entity.setIssueDate(dto.getIssueDate());
        entity.setBillingDays(dto.getBillingDays());
        entity.setAutomaticDebitCode(dto.getAutomaticDebitCode());
        entity.setMeterId(dto.getMeterId());
        entity.setMonthDelay(dto.getMonthDelay());
        entity.setValueDelay(dto.getValueDelay());
        entity.setTensionGroup(dto.getTensionGroup());
        entity.setFareSubGroup(dto.getFareSubGroup());
        entity.setAnnotations(dto.getAnnotations());
        entity.setSubscriptionId(dto.getSubscriptionId());
        entity.setCustomer(savedCustomer);
        entity.setDistributor(distributorSaved);
        entity.setItems(dto.getItems().stream().map(EntityDtoMapper::convertToEntity).collect(Collectors.toList()));
        return entity;
    }

    public static InvoiceItemEntity convertToEntity(InvoiceItemDTO dto) {
        InvoiceItemEntity entity = new InvoiceItemEntity();
        entity.setSdpId(dto.getSdpId());
        entity.setUc(dto.getUc());
        entity.setSite(dto.getSite());
        entity.setBillSource(dto.getBillSource());
        entity.setSuppliedVoltageType(dto.getSuppliedVoltageType());
        entity.setBillCalculationTrigger(dto.getBillCalculationTrigger());
        entity.setTariffType(dto.getTariffType());
        entity.setBillClass(dto.getBillClass());
        entity.setInsertedAt(dto.getInsertedAt());
        entity.setReferenceDate(dto.getReferenceDate());
        entity.setReadingBegin(dto.getReadingBegin());
        entity.setReadingEnd(dto.getReadingEnd());
        entity.setReadingDate(dto.getReadingDate());
        entity.setPreviousReading(dto.getPreviousReading());
        entity.setNextReading(dto.getNextReading());
        entity.setDueDate(dto.getDueDate());
//        entity.setBillableItems(dto.getBillableItems());
//        entity.setTaxItems(dto.getTaxItems());
//        entity.setOtherItems(dto.getOtherItems());
//        entity.setQualityItems(dto.getQualityItems());
        entity.setHasCustomData(dto.getHasCustomData());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setTotalExceptTaxes(dto.getTotalExceptTaxes());
        entity.setTaxes(dto.getTaxes());
        entity.setOthers(dto.getOthers());
        entity.setValidated(dto.getValidated());
        entity.setNumberOfFiles(dto.getNumberOfFiles());
        entity.setAlerts(dto.getAlerts());
        entity.setTotalDays(dto.getTotalDays());
        entity.setReadingBeginKwh(dto.getReadingBeginKwh());
        entity.setReadingEndKwh(dto.getReadingEndKwh());
        entity.setConstant(dto.getConstant());
        entity.setBarcode(dto.getBarcode());
        entity.setSubTotalAmount(dto.getSubTotalAmount());
        entity.setContractNumber(dto.getContractNumber());
        entity.setInstallationId(dto.getInstallationId());
        entity.setClassType(dto.getClassType());
        entity.setSubClass(dto.getSubClass());
        return entity;
    }

    public static CustomerEntity convertToEntity(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(dto.getCustomerId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setPublicPlace(dto.getPublicPlace());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setZipCode(dto.getZipCode());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        return entity;
    }

    public static DistributorEntity convertToEntity(DistributorDTO dto) {
        DistributorEntity entity = new DistributorEntity();
        entity.setDistributorIdValue(dto.getDistributorIdValue());
        entity.setEnergyProvider(dto.getEnergyProvider());
        return entity;
    }

}
