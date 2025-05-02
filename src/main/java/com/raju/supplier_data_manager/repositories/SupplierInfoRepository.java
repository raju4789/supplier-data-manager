package com.raju.supplier_data_manager.repositories;

import com.raju.supplier_data_manager.entities.SupplierInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierInfoRepository extends JpaRepository<SupplierInfo, Integer> {
    // You can add custom query methods here if needed
}
