package com.raju.supplier_data_manager.repositories;

import com.raju.supplier_data_manager.entities.PartnerContactConfigInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerContactConfigInfoRepository extends JpaRepository<PartnerContactConfigInfo, Integer> {
    // Custom queries if needed
}
