package com.raju.supplier_data_manager.repositories;

import com.raju.supplier_data_manager.entities.AirlineContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineContactRepository extends JpaRepository<AirlineContact, Integer> {
    // Custom queries if needed
}
