package com.raju.supplier_data_manager.dtos.supplier;

import com.raju.supplier_data_manager.annotations.OnSupplierCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirlineContactInput {
    private Integer id;

    @NotNull(groups = OnSupplierCreate.class)
    private String carrierCode;

    private String airlinePostBookingUrl;
    private String airlineBaggageUrl;
    private String airlineContactPageUrl;
}
