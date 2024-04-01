package com.example.demo;

import com.example.demo.dto.*;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.DistributorEntity;
import com.example.demo.entity.InvoiceEntity;
import com.example.demo.entity.InvoiceItemEntity;
import com.example.demo.mapper.EntityDtoMapper;
import com.example.demo.repository.ICustomerRepository;
import com.example.demo.repository.IDistributorRepository;
import com.example.demo.repository.IInvoiceItemRepository;
import com.example.demo.repository.IInvoiceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.CheckAndConvertValues.*;

@RestController
public class EnergyBillController {

    private final Way2Config way2Config;
    @Autowired
    ExternalApiClient externalApiClient;

    private final ObjectMapper objectMapper;

    private final IInvoiceRepository iInvoiceRepository;

    private final ICustomerRepository iCustomerRepository;

    private final IDistributorRepository iDistributorRepository;

    private final IInvoiceItemRepository iInvoiceItemRepository;


    public EnergyBillController(
            Way2Config way2Config,
            ExternalApiClient externalApiClient, ObjectMapper objectMapper, IInvoiceRepository iInvoiceRepository, ICustomerRepository iCustomerRepository, IDistributorRepository iDistributorRepository, IInvoiceItemRepository iInvoiceItemRepository
    ) {
        this.way2Config = way2Config;
        this.externalApiClient = externalApiClient;
        this.objectMapper = objectMapper;
        this.iInvoiceRepository = iInvoiceRepository;
        this.iCustomerRepository = iCustomerRepository;
        this.iDistributorRepository = iDistributorRepository;
        this.iInvoiceItemRepository = iInvoiceItemRepository;
    }

    @PostMapping("/create-energy-bill")
    public ResponseEntity<String> getEnergyBills(
            @RequestBody EnergyBillRequest requestBody
    ) {
        // Extract subscriptionId from the requestBody
        String subscriptionId = requestBody.getSubscriptionId();

        // Set headers
        String accept = MediaType.APPLICATION_JSON_VALUE;
        String contentType = MediaType.APPLICATION_JSON_VALUE;
        String apiVersion = way2Config.getApiVersion();
        String apiKey = way2Config.getApiKey();

        // Call Feign client to make the API request
        String response = externalApiClient.getEnergyBills(
                requestBody,
                accept,
                contentType,
                apiVersion,
                apiKey,
                subscriptionId
        );

        // Write response to JSON file
        try {
            writeResponseToFile(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Return ResponseEntity with response body
        return ResponseEntity.ok(response);
    }

    private void writeResponseToFile(String response) throws JsonProcessingException {

        JsonNode jsonNode;

        jsonNode = objectMapper.readValue(response, JsonNode.class);

        JsonNode items = getItems(jsonNode);

        int itemCount = jsonNode.size();
        System.out.println(">>> NUMERO DE ITENS : " + itemCount);

        System.out.println(items);

        processItems(
                items
        );



    }

    private JsonNode getItems(JsonNode jsonNode) {
        return Optional.ofNullable(jsonNode)
                .map(j -> j.get("items"))
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid JSON Object")
                );

    }

    private void processItems(JsonNode items) {

        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        for (JsonNode item : items) {
            invoiceDTOList.add(createItemsFromNode(item));
        }
    }

    private InvoiceDTO createItemsFromNode(JsonNode item) {

        System.out.println(">>> PASSEI AQUI "  );


        List<InvoiceItemDTO> invoiceItemList = new ArrayList<>();

        List<BillableItemDTO> billableItemList = new ArrayList<>();

        List<TaxItemDTO> taxItemList = new ArrayList<>();

        List<OtherItemDTO> otherItemsList = new ArrayList<>();

        List<QualityItemDTO> qualityItemsList = new ArrayList<>();

        Long invoiceItemId = getLongReturnFromCheckValue(
                item.get("invoiceItemId")
        );

        String invoiceSeries = getStringReturnFromCheckValueAsText(
                item.get("invoiceSeries")
        );

        LocalDateTime issueDate = convertDateTime(
                item.get("issueDate").asText()
        );

        int billingDays = getIntReturnFromCheckValue(
                item.get("billingDays")
        );

        String automaticDebitCode = getStringReturnFromCheckValueAsText(
                item.get("automaticDebitCode")
        );

        String meterId = getStringReturnFromCheckValueAsText(
                item.get("meterId")
        );

        String monthDelay = getStringReturnFromCheckValueAsText(
                item.get("monthDelay")
        );

        String valueDelay = getStringReturnFromCheckValueAsText(
                item.get("valueDelay")
        );

        String tensionGroup = getStringReturnFromCheckValueAsText(
                item.get("tensionGroup")
        );

        String fareSubGroup = getStringReturnFromCheckValueAsText(
                item.get("fareSubGroup")
        );

        String annotations = getStringReturnFromCheckValueAsText(
                item.get("annotations")
        );

        String subscriptionId = getStringReturnFromCheckValueAsText(
                item.get("subscriptionId")
        );

        Long customerId = getLongReturnFromCheckValue(
                item.get("customerId")
        );

        String customerName = getStringReturnFromCheckValueAsText(
                item.get("customerName")
        );

        JsonNode addressNode = item.get("address");

        String publicPlace = getStringReturnFromCheckValueAsText(
                addressNode.get("publicPlace")
        );

        String neighborhood = getStringReturnFromCheckValueAsText(
                addressNode.get("neighborhood")
        );

        String zipCode = getStringReturnFromCheckValueAsText(
                addressNode.get("zipCode")
        );

        String city = getStringReturnFromCheckValueAsText(
                addressNode.get("city")
        );

        String state = getStringReturnFromCheckValueAsText(
                addressNode.get("state")
        );


        CustomerDTO customerDTO = new CustomerDTO(
                customerId,
                customerName,
                publicPlace,
                neighborhood,
                zipCode,
                city,
                state
        );

        CustomerEntity customerEntity = EntityDtoMapper.convertToEntity(customerDTO);
        iCustomerRepository.save(customerEntity);

        CustomerEntity savedCustomer = iCustomerRepository.save(customerEntity);

        String cnpjDistributor = getStringReturnFromCheckValueAsText(
                item.get("cnpjDistributor")
        );

        String energyProvider = getStringReturnFromCheckValueAsText(
                item.get("energyProvider")
        );

        DistributorDTO distributorDTO = new DistributorDTO(
                cnpjDistributor,
                energyProvider
        );

        DistributorEntity distributorEntity = EntityDtoMapper.convertToEntity(distributorDTO);

        iDistributorRepository.save(distributorEntity);

        DistributorEntity distributorSaved = iDistributorRepository.save(distributorEntity);

        String sdpId = getStringReturnFromCheckValueAsText(
                item.get("sdpId")
        );

        String uc = getStringReturnFromCheckValueAsText(
                item.get("uc")
        );

        String site = getStringReturnFromCheckValueAsText(
                item.get("site")
        );
        String billSource = getStringReturnFromCheckValueAsText(
                item.get("billSource")
        );

        String suppliedVoltageType = getStringReturnFromCheckValueAsText(
                item.get("suppliedVoltageType")
        );

        String billCalculationTrigger = getStringReturnFromCheckValueAsText(
                item.get("billCalculationTrigger")
        );

        String tariffType = getStringReturnFromCheckValueAsText(
                item.get("tariffType")
        );

        String billClass = getStringReturnFromCheckValueAsText(
                item.get("billClass")
        );

        LocalDateTime insertedAt = convertDateTime(
                item.get("insertedAt").asText()
        );

        LocalDateTime referenceDate = convertDateTime(
                item.get("referenceDate").asText()
        );

        LocalDateTime readingBegin = convertDateTime(
                item.get("readingBegin").asText()
        );

        LocalDateTime readingEnd = convertDateTime(
                item.get("readingEnd").asText()
        );

        LocalDateTime readingDate = convertDateTime(
                item.get("readingDate").asText()
        );

        LocalDateTime previousReading = convertDateTime(
                item.get("previousReading").asText()
        );

        LocalDateTime nextReading = convertDateTime(
                item.get("nextReading").asText()
        );

        LocalDateTime dueDate = convertDateTime(
                item.get("dueDate").asText()
        );

        List<BillableItemDTO> billableItemArrayList = new ArrayList<>();

        billableItemList = processBillableItems(
                item,
                billableItemArrayList
        );

        List<TaxItemDTO> taxItemArrayList = new ArrayList<>();

        taxItemList = processTaxItems(
                item,
                taxItemArrayList
        );

        List<OtherItemDTO> otherItemsArrayList = new ArrayList<>();

        otherItemsList = processOtherItems(
                item,
                otherItemsArrayList
        );

        List<QualityItemDTO> qualityItemArrayList = new ArrayList<>();

        qualityItemsList = processQualityItems(
                item,
                qualityItemArrayList
        );

        boolean hasCustomData = getBooleanReturnFromCheckValue(
                item.get("hasCustomData")
        );

        BigDecimal totalAmount = getBigDecimalReturnFromCheckValue(
                item.get("totalAmount")
        );

        BigDecimal totalExceptTaxes = getBigDecimalReturnFromCheckValue(
                item.get("totalExceptTaxes")
        );

        BigDecimal taxes = getBigDecimalReturnFromCheckValue(
                item.get("taxes")
        );

        BigDecimal others = getBigDecimalReturnFromCheckValue(
                item.get("others")
        );

        boolean validated = getBooleanReturnFromCheckValue(
                item.get("validated")
        );

        int numberOfFiles = getIntReturnFromCheckValue(
                item.get("numberOfFiles")
        );

        int alerts = getIntReturnFromCheckValue(
                item.get("alerts")
        );

        int totalDays = getIntReturnFromCheckValue(
                item.get("totalDays")
        );

        BigDecimal readingBeginKwh = getBigDecimalReturnFromCheckValue(
                item.get("readingBeginKwh")
        );

        BigDecimal readingEndKwh = getBigDecimalReturnFromCheckValue(
                item.get("readingEndKwh")
        );

        BigDecimal constant = getBigDecimalReturnFromCheckValue(
                item.get("constant")
        );

        String barcode = getStringReturnFromCheckValueAsText(
                item.get("barcode")
        );

        BigDecimal subTotalAmount = getBigDecimalReturnFromCheckValue(
                item.get("subTotalAmount")
        );

        String contractNumber = getStringReturnFromCheckValueAsText(
                item.get("contractNumber")
        );

        String installationId = getStringReturnFromCheckValueAsText(
                item.get("installationId")
        );

        String className = getStringReturnFromCheckValueAsText(
                item.get("class")
        );

        String subClass = getStringReturnFromCheckValueAsText(
                item.get("subClass")
        );

        InvoiceItemDTO invoiceItemDTO =
                new InvoiceItemDTO(
                        sdpId,
                        uc,
                        site,
                        billSource,
                        suppliedVoltageType,
                        billCalculationTrigger,
                        tariffType,
                        billClass,
                        insertedAt,
                        referenceDate,
                        readingBegin,
                        readingEnd,
                        readingDate,
                        previousReading,
                        nextReading,
                        dueDate,
                        billableItemList,
                        taxItemList,
                        otherItemsList,
                        qualityItemsList,
                        hasCustomData,
                        totalAmount,
                        totalExceptTaxes,
                        taxes,
                        others,
                        validated,
                        numberOfFiles,
                        alerts,
                        totalDays,
                        readingBeginKwh,
                        readingEndKwh,
                        constant,
                        barcode,
                        subTotalAmount,
                        contractNumber,
                        installationId,
                        className,
                        subClass
                );

        invoiceItemList.add(invoiceItemDTO);

        System.out.println(invoiceDTOList);

        InvoiceItemEntity invoiceItemEntity = EntityDtoMapper.convertToEntity(invoiceItemDTO);
        iInvoiceItemRepository.save(invoiceItemEntity);

        InvoiceDTO invoiceDTO =
                new InvoiceDTO(
                        invoiceSeries,
                        issueDate,
                        billingDays,
                        automaticDebitCode,
                        meterId,
                        monthDelay,
                        valueDelay,
                        tensionGroup,
                        fareSubGroup,
                        annotations,
                        subscriptionId,
                        customerDTO,
                        distributorDTO,
                        invoiceItemList
                );

        InvoiceEntity invoiceEntity = EntityDtoMapper.convertToEntity(invoiceDTO, savedCustomer,distributorSaved);

        iInvoiceRepository.save(invoiceEntity);

        return invoiceDTO;
    }

    private List<BillableItemDTO> processBillableItems(
            JsonNode item,
            List<BillableItemDTO> billableItemList
    ) {
        // BillableItems
        JsonNode billableItems = item.get("billableItems");

        for (JsonNode billableItem : billableItems) {

            String area = getStringReturnFromCheckValueAsText(
                    billableItem.get("area")
            );

            String name = getStringReturnFromCheckValueAsText(
                    billableItem.get("name")
            );

            BigDecimal measured = getBigDecimalReturnFromCheckValue(
                    billableItem.get("measured")
            );

            BigDecimal contract = getBigDecimalReturnFromCheckValue(
                    billableItem.get("contract")
            );

            BigDecimal billed = getBigDecimalReturnFromCheckValue(
                    billableItem.get("billed")
            );

            BigDecimal value = getBigDecimalReturnFromCheckValue(
                    billableItem.get("value")
            );

            String previousMeterRead = getStringReturnFromCheckValueAsText(
                    billableItem.get("previousMeterRead")
            );

            String currentMeterRead = getStringReturnFromCheckValueAsText(
                    billableItem.get("currentMeterRead")
            );

            String meterMultiplier = getStringReturnFromCheckValueAsText(
                    billableItem.get("meterMultiplier")
            );

            BigDecimal tariff = getBigDecimalReturnFromCheckValue(
                    billableItem.get("tariff")
            );

            boolean hasCustomData = getBooleanReturnFromCheckValue(
                    billableItem.get("hasCustomData")
            );

            int quality = getIntReturnFromCheckValue(
                    billableItem.get("quality")
            );

            billableItemList.add(
                    new BillableItemDTO(
                            area,
                            name,
                            measured,
                            contract,
                            billed,
                            value,
                            previousMeterRead,
                            currentMeterRead,
                            meterMultiplier,
                            tariff,
                            hasCustomData,
                            quality
                    )
            );
        }

        return billableItemList;

    }

    private List<TaxItemDTO> processTaxItems(
            JsonNode item,
            List<TaxItemDTO> taxItemList
    ) {
        // TaxItems
        JsonNode taxItems = item.get("taxItems");

        for (JsonNode taxItem : taxItems) {

            String taxItemName = getStringReturnFromCheckValueAsText(
                    taxItem.get("taxItemName")
            );

            BigDecimal taxableValue = getBigDecimalReturnFromCheckValue(
                    taxItem.get("taxableValue")
            );

            BigDecimal taxRate = getBigDecimalReturnFromCheckValue(
                    taxItem.get("taxRate")
            );

            BigDecimal taxItemValue = getBigDecimalReturnFromCheckValue(
                    taxItem.get("taxItemValue")
            );

            boolean hasCustomData = getBooleanReturnFromCheckValue(
                    taxItem.get("hasCustomData")
            );
//            TODO
//            LocalDateTime taxValidSince = convertDateTime(
//                    taxItem.get("taxValidSince").asText()
//            );

            String taxItemQuality = getStringReturnFromCheckValueAsText(
                    taxItem.get("quality")
            );

            taxItemList.add(
                    new TaxItemDTO(
                            taxItemName,
                            taxableValue,
                            taxRate,
                            taxItemValue,
                            hasCustomData,
                            null,
                            taxItemQuality
                    )
            );
        }

        return taxItemList;

    }

    private List<QualityItemDTO> processQualityItems(
            JsonNode item,
            List<QualityItemDTO> qualityItemArrayList
    ) {

        // QualityItems
        JsonNode qualityItems = item.get("qualityItems");
        for (JsonNode qualityItem : qualityItems) {

            String name = getStringReturnFromCheckValueAsText(
                    qualityItem.get("name")
            );

            BigDecimal value = getBigDecimalReturnFromCheckValue(
                    qualityItem.get("value")
            );

            boolean hasCustomData = getBooleanReturnFromCheckValue(
                    qualityItems.get("hasCustomData")
            );

            qualityItemArrayList.add(
                    new QualityItemDTO(
                            name,
                            value
                    )
            );
        }
        return qualityItemArrayList;
    }

    private List<OtherItemDTO> processOtherItems(
            JsonNode item,
            List<OtherItemDTO> otherItemsList
    ) {
        // OtherItems
        JsonNode otherItems = item.get("otherItems");
        for (JsonNode otherItem : otherItems) {

            String name = getStringReturnFromCheckValueAsText(
                    otherItem.get("name")
            );

            BigDecimal value = getBigDecimalReturnFromCheckValue(
                    otherItem.get("value")
            );

            boolean hasCustomData = getBooleanReturnFromCheckValue(
                    otherItem.get("hasCustomData")
            );

            otherItemsList.add(
                    new OtherItemDTO(
                            name,
                            value,
                            hasCustomData
                    )
            );
        }

        return otherItemsList;
    }


}