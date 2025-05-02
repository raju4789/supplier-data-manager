package com.raju.supplier_data_manager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "airline_contact")
@Data
public class AirlineContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String carrierCode;

    private String airlinePostBookingUrl;
    private String airlineBaggageUrl;
    private String airlineContactPageUrl;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierInfo supplierInfo;

}
