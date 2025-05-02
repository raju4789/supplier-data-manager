package com.raju.supplier_data_manager.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier_info")
@Data
public class SupplierInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private String supplierType;

    private String supplierFromEmail;

    @Column(nullable = false)
    private String supplierGroup;

    @Column(nullable = false)
    private String subSupplierGroup;

    @Column(nullable = false)
    private String sopModel;

    @Column(nullable = false)
    private Boolean pbfFees;

    private Double feeAmount;
    private String pointOfSale;
    private String gdsPcc;
    private Integer iataNumber;
    private String gdsPrinter;
    private String slaInContract;
    private String sopUrl;

    @Column(nullable = false, columnDefinition = "CHAR(36)")
    private String createdBy;

    @OneToMany(mappedBy = "supplierInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartnerConfigInfo> partnerConfigInfo = new ArrayList<>();

    @OneToMany(mappedBy = "supplierInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartnerContactConfigInfo> partnerContactConfigInfo = new ArrayList<>();

    @OneToMany(mappedBy = "supplierInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AirlineContact> airlineContact = new ArrayList<>();

}
