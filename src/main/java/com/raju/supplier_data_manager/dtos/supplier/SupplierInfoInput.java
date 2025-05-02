package com.raju.supplier_data_manager.dtos.supplier;

import com.raju.supplier_data_manager.annotations.OnSupplierCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SupplierInfoInput {

    @NotNull(groups = OnSupplierCreate.class)
    private String supplierName;

    @NotNull(groups = OnSupplierCreate.class)
    private String supplierType;

    @NotNull(groups = OnSupplierCreate.class)
    private String supplierFromEmail;

    @NotNull(groups = OnSupplierCreate.class)
    private String supplierGroup;

    @NotNull(groups = OnSupplierCreate.class)
    private String subSupplierGroup;

    @NotNull(groups = OnSupplierCreate.class)
    private String sopModel;

    private Boolean pbfFees;

    private Double feeAmount;

    private String pointOfSale;

    private String gdsPcc;

    private Integer iataNumber;

    private String gdsPrinter;

    private String slaInContract;

    private String sopUrl;

    @NotNull(groups = OnSupplierCreate.class)
    private String createdBy;

    private List<PartnerConfigInfoInput> partnerConfigInfo;

    private List<PartnerContactConfigInfoInput> partnerContactConfigInfo;

    private List<AirlineContactInput> airlineContact;
}
