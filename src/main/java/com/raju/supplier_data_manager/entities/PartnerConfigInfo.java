package com.raju.supplier_data_manager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "partner_config_info")
@Data
public class PartnerConfigInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private Integer cegContactChannelId;

    @Column(nullable = false)
    private Integer cegContactPartyId;

    private String partnerNotes;
    private Integer whiteLabelId;
    private String ticketingAirlineCode;
    private String pointOfSale;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierInfo supplierInfo;

    // getters and setters
}
