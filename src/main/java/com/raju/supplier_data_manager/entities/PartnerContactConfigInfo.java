package com.raju.supplier_data_manager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "partner_contact_config_info")
@Data
public class PartnerContactConfigInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime operatingHoursFrom;
    private LocalDateTime operatingHoursTo;
    private String operatingDays;

    @Column(nullable = false)
    private String gmtLocal;

    @Column(nullable = false)
    private Integer opsHourType;

    @Column(nullable = false)
    private Integer contactType;

    @Column(nullable = false)
    private String contactValue;

    private String contactNotes;
    private String countryName;
    private String languageName;
    private Integer bookingOrigin;
    private String internalUsage;
    private Integer contactCategoryId;
    private Boolean isPcaEmail;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonBackReference
    private SupplierInfo supplierInfo;

}
