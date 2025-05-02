package com.raju.supplier_data_manager.dtos.supplier;

import com.raju.supplier_data_manager.annotations.OnSupplierCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartnerContactConfigInfoInput {
    private Integer id;
    private String operatingHoursFrom;
    private String operatingHoursTo;
    private String operatingDays;

    @NotNull(groups = OnSupplierCreate.class)
    private String gmtLocal;

    @NotNull(groups = OnSupplierCreate.class)
    private Integer opsHourType;

    @NotNull(groups = OnSupplierCreate.class)
    private Integer contactType;

    @NotNull(groups = OnSupplierCreate.class)
    private String contactValue;

    private String contactNotes;
    private String countryName;
    private String languageName;
    private Integer bookingOrigin;
    private String internalUsage;
    private Integer contactCategoryId;
    private Boolean isPcaEmail;
}
