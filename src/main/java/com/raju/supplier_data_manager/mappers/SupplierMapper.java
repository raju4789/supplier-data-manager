package com.raju.supplier_data_manager.mappers;

import com.raju.supplier_data_manager.dtos.supplier.AirlineContactInput;
import com.raju.supplier_data_manager.dtos.supplier.PartnerConfigInfoInput;
import com.raju.supplier_data_manager.dtos.supplier.PartnerContactConfigInfoInput;
import com.raju.supplier_data_manager.dtos.supplier.SupplierInfoInput;
import com.raju.supplier_data_manager.entities.AirlineContact;
import com.raju.supplier_data_manager.entities.PartnerConfigInfo;
import com.raju.supplier_data_manager.entities.PartnerContactConfigInfo;
import com.raju.supplier_data_manager.entities.SupplierInfo;

import java.time.LocalDateTime;


public class SupplierMapper {

    public static SupplierInfo toEntity(SupplierInfoInput input) {
        SupplierInfo supplier = new SupplierInfo();
        updateEntity(input, supplier);
        return supplier;
    }

    public static void updateEntity(SupplierInfoInput input, SupplierInfo supplier) {
        supplier.setSupplierName(input.getSupplierName());
        supplier.setSupplierType(input.getSupplierType());
        supplier.setSupplierFromEmail(input.getSupplierFromEmail());
        supplier.setSupplierGroup(input.getSupplierGroup());
        supplier.setSubSupplierGroup(input.getSubSupplierGroup());
        supplier.setSopModel(input.getSopModel());
        supplier.setPbfFees(input.getPbfFees());
        supplier.setFeeAmount(input.getFeeAmount());
        supplier.setPointOfSale(input.getPointOfSale());
        supplier.setGdsPcc(input.getGdsPcc());
        supplier.setIataNumber(input.getIataNumber());
        supplier.setGdsPrinter(input.getGdsPrinter());
        supplier.setSlaInContract(input.getSlaInContract());
        supplier.setSopUrl(input.getSopUrl());
        supplier.setCreatedBy(input.getCreatedBy());

        // PartnerConfigInfo
        supplier.getPartnerConfigInfo().clear();
        if (input.getPartnerConfigInfo() != null) {
            supplier.getPartnerConfigInfo().addAll(
                    input.getPartnerConfigInfo().stream()
                            .map(pciInput -> {
                                PartnerConfigInfo pci = toEntity(pciInput);
                                pci.setSupplierInfo(supplier);
                                return pci;
                            })
                            .toList()
            );
        }

        // PartnerContactConfigInfo
        supplier.getPartnerContactConfigInfo().clear();
        if (input.getPartnerContactConfigInfo() != null) {
            supplier.getPartnerContactConfigInfo().addAll(
                    input.getPartnerContactConfigInfo().stream()
                            .map(pcciInput -> {
                                PartnerContactConfigInfo pcci = toEntity(pcciInput);
                                pcci.setSupplierInfo(supplier);
                                return pcci;
                            })
                            .toList()
            );
        }

        // AirlineContact
        supplier.getAirlineContact().clear();
        if (input.getAirlineContact() != null) {
            supplier.getAirlineContact().addAll(
                    input.getAirlineContact().stream()
                            .map(acInput -> {
                                AirlineContact ac = toEntity(acInput);
                                ac.setSupplierInfo(supplier);
                                return ac;
                            })
                            .toList()
            );
        }
    }

    public static void partialUpdateEntity(SupplierInfoInput input, SupplierInfo supplier) {
        if (input.getSupplierName() != null) supplier.setSupplierName(input.getSupplierName());
        if (input.getSupplierType() != null) supplier.setSupplierType(input.getSupplierType());
        if (input.getSupplierFromEmail() != null) supplier.setSupplierFromEmail(input.getSupplierFromEmail());
        if (input.getSupplierGroup() != null) supplier.setSupplierGroup(input.getSupplierGroup());
        if (input.getSubSupplierGroup() != null) supplier.setSubSupplierGroup(input.getSubSupplierGroup());
        if (input.getSopModel() != null) supplier.setSopModel(input.getSopModel());
        if (input.getPbfFees() != null) supplier.setPbfFees(input.getPbfFees());
        if (input.getFeeAmount() != null) supplier.setFeeAmount(input.getFeeAmount());
        if (input.getPointOfSale() != null) supplier.setPointOfSale(input.getPointOfSale());
        if (input.getGdsPcc() != null) supplier.setGdsPcc(input.getGdsPcc());
        if (input.getIataNumber() != null) supplier.setIataNumber(input.getIataNumber());
        if (input.getGdsPrinter() != null) supplier.setGdsPrinter(input.getGdsPrinter());
        if (input.getSlaInContract() != null) supplier.setSlaInContract(input.getSlaInContract());
        if (input.getSopUrl() != null) supplier.setSopUrl(input.getSopUrl());
        if (input.getCreatedBy() != null) supplier.setCreatedBy(input.getCreatedBy());

        // --- PartnerConfigInfo partial update ---
        if (input.getPartnerConfigInfo() != null) {
            for (PartnerConfigInfoInput pciInput : input.getPartnerConfigInfo()) {
                if (pciInput.getId() != null) {
                    // Try to find existing by id
                    PartnerConfigInfo existing = supplier.getPartnerConfigInfo().stream()
                            .filter(pci -> pci.getId().equals(pciInput.getId()))
                            .findFirst()
                            .orElse(null);
                    if (existing != null) {
                        // Partial update existing
                        partialUpdatePartnerConfigInfo(pciInput, existing);
                    } else {
                        // Add new if not found
                        PartnerConfigInfo newPci = toEntity(pciInput);
                        newPci.setSupplierInfo(supplier);
                        supplier.getPartnerConfigInfo().add(newPci);
                    }
                } else {
                    // Add new if no id
                    PartnerConfigInfo newPci = toEntity(pciInput);
                    newPci.setSupplierInfo(supplier);
                    supplier.getPartnerConfigInfo().add(newPci);
                }
            }
        }

        // --- PartnerContactConfigInfo partial update ---
        if (input.getPartnerContactConfigInfo() != null) {
            for (PartnerContactConfigInfoInput pcciInput : input.getPartnerContactConfigInfo()) {
                if (pcciInput.getId() != null) {
                    PartnerContactConfigInfo existing = supplier.getPartnerContactConfigInfo().stream()
                            .filter(pcci -> pcci.getId().equals(pcciInput.getId()))
                            .findFirst()
                            .orElse(null);
                    if (existing != null) {
                        partialUpdatePartnerContactConfigInfo(pcciInput, existing);
                    } else {
                        PartnerContactConfigInfo newPcci = toEntity(pcciInput);
                        newPcci.setSupplierInfo(supplier);
                        supplier.getPartnerContactConfigInfo().add(newPcci);
                    }
                } else {
                    PartnerContactConfigInfo newPcci = toEntity(pcciInput);
                    newPcci.setSupplierInfo(supplier);
                    supplier.getPartnerContactConfigInfo().add(newPcci);
                }
            }
        }

        // --- AirlineContact partial update ---
        if (input.getAirlineContact() != null) {
            for (AirlineContactInput acInput : input.getAirlineContact()) {
                if (acInput.getId() != null) {
                    AirlineContact existing = supplier.getAirlineContact().stream()
                            .filter(ac -> ac.getId().equals(acInput.getId()))
                            .findFirst()
                            .orElse(null);
                    if (existing != null) {
                        partialUpdateAirlineContact(acInput, existing);
                    } else {
                        AirlineContact newAc = toEntity(acInput);
                        newAc.setSupplierInfo(supplier);
                        supplier.getAirlineContact().add(newAc);
                    }
                } else {
                    AirlineContact newAc = toEntity(acInput);
                    newAc.setSupplierInfo(supplier);
                    supplier.getAirlineContact().add(newAc);
                }
            }
        }
    }

// --- Helper methods for partial update of nested objects ---

    private static void partialUpdatePartnerConfigInfo(PartnerConfigInfoInput input, PartnerConfigInfo entity) {
        if (input.getProductId() != null) entity.setProductId(input.getProductId());
        if (input.getCegContactChannelId() != null) entity.setCegContactChannelId(input.getCegContactChannelId());
        if (input.getCegContactPartyId() != null) entity.setCegContactPartyId(input.getCegContactPartyId());
        if (input.getPartnerNotes() != null) entity.setPartnerNotes(input.getPartnerNotes());
        if (input.getWhiteLabelId() != null) entity.setWhiteLabelId(input.getWhiteLabelId());
        if (input.getTicketingAirlineCode() != null) entity.setTicketingAirlineCode(input.getTicketingAirlineCode());
        if (input.getPointOfSale() != null) entity.setPointOfSale(input.getPointOfSale());
    }

    private static void partialUpdatePartnerContactConfigInfo(PartnerContactConfigInfoInput input, PartnerContactConfigInfo entity) {
        if (input.getOperatingHoursFrom() != null) entity.setOperatingHoursFrom(LocalDateTime.parse(input.getOperatingHoursFrom()));
        if (input.getOperatingHoursTo() != null) entity.setOperatingHoursTo(LocalDateTime.parse(input.getOperatingHoursTo()));
        if (input.getOperatingDays() != null) entity.setOperatingDays(input.getOperatingDays());
        if (input.getGmtLocal() != null) entity.setGmtLocal(input.getGmtLocal());
        if (input.getOpsHourType() != null) entity.setOpsHourType(input.getOpsHourType());
        if (input.getContactType() != null) entity.setContactType(input.getContactType());
        if (input.getContactValue() != null) entity.setContactValue(input.getContactValue());
        if (input.getContactNotes() != null) entity.setContactNotes(input.getContactNotes());
        if (input.getCountryName() != null) entity.setCountryName(input.getCountryName());
        if (input.getLanguageName() != null) entity.setLanguageName(input.getLanguageName());
        if (input.getBookingOrigin() != null) entity.setBookingOrigin(input.getBookingOrigin());
        if (input.getInternalUsage() != null) entity.setInternalUsage(input.getInternalUsage());
        if (input.getContactCategoryId() != null) entity.setContactCategoryId(input.getContactCategoryId());
        if (input.getIsPcaEmail() != null) entity.setIsPcaEmail(input.getIsPcaEmail());
    }

    private static void partialUpdateAirlineContact(AirlineContactInput input, AirlineContact entity) {
        if (input.getCarrierCode() != null) entity.setCarrierCode(input.getCarrierCode());
        if (input.getAirlinePostBookingUrl() != null) entity.setAirlinePostBookingUrl(input.getAirlinePostBookingUrl());
        if (input.getAirlineBaggageUrl() != null) entity.setAirlineBaggageUrl(input.getAirlineBaggageUrl());
        if (input.getAirlineContactPageUrl() != null) entity.setAirlineContactPageUrl(input.getAirlineContactPageUrl());
    }

    // --- Nested Entity Mappers ---

    public static PartnerConfigInfo toEntity(PartnerConfigInfoInput input) {
        PartnerConfigInfo pci = new PartnerConfigInfo();
        pci.setProductId(input.getProductId());
        pci.setCegContactChannelId(input.getCegContactChannelId());
        pci.setCegContactPartyId(input.getCegContactPartyId());
        pci.setPartnerNotes(input.getPartnerNotes());
        pci.setWhiteLabelId(input.getWhiteLabelId());
        pci.setTicketingAirlineCode(input.getTicketingAirlineCode());
        pci.setPointOfSale(input.getPointOfSale());
        return pci;
    }

    public static PartnerContactConfigInfo toEntity(PartnerContactConfigInfoInput input) {
        PartnerContactConfigInfo pcci = new PartnerContactConfigInfo();
        pcci.setOperatingHoursFrom(parseDateTime(input.getOperatingHoursFrom()));
        pcci.setOperatingHoursTo(parseDateTime(input.getOperatingHoursTo()));
        pcci.setOperatingDays(input.getOperatingDays());
        pcci.setGmtLocal(input.getGmtLocal());
        pcci.setOpsHourType(input.getOpsHourType());
        pcci.setContactType(input.getContactType());
        pcci.setContactValue(input.getContactValue());
        pcci.setContactNotes(input.getContactNotes());
        pcci.setCountryName(input.getCountryName());
        pcci.setLanguageName(input.getLanguageName());
        pcci.setBookingOrigin(input.getBookingOrigin());
        pcci.setInternalUsage(input.getInternalUsage());
        pcci.setContactCategoryId(input.getContactCategoryId());
        pcci.setIsPcaEmail(input.getIsPcaEmail());
        return pcci;
    }

    public static AirlineContact toEntity(AirlineContactInput input) {
        AirlineContact ac = new AirlineContact();
        ac.setCarrierCode(input.getCarrierCode());
        ac.setAirlinePostBookingUrl(input.getAirlinePostBookingUrl());
        ac.setAirlineBaggageUrl(input.getAirlineBaggageUrl());
        ac.setAirlineContactPageUrl(input.getAirlineContactPageUrl());
        return ac;
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null) return null;
        return LocalDateTime.parse(dateTimeStr); // expects ISO-8601 format
    }
}
