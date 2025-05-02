package com.raju.supplier_data_manager.dtos.supplier;

import com.raju.supplier_data_manager.annotations.OnSupplierCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartnerConfigInfoInput {
    private Integer id;

    @NotNull(groups = OnSupplierCreate.class)
    private Integer productId;

    @NotNull(groups = OnSupplierCreate.class)
    private Integer cegContactChannelId;

    @NotNull(groups = OnSupplierCreate.class)
    private Integer cegContactPartyId;

    private String partnerNotes;
    private Integer whiteLabelId;
    private String ticketingAirlineCode;
    private String pointOfSale;
}
