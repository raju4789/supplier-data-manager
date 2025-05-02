package com.raju.supplier_data_manager.services;

import com.raju.supplier_data_manager.dtos.supplier.SupplierInfoInput;
import com.raju.supplier_data_manager.entities.SupplierInfo;
import com.raju.supplier_data_manager.exceptions.SupplierNotFoundException;
import com.raju.supplier_data_manager.mappers.SupplierMapper;
import com.raju.supplier_data_manager.repositories.SupplierInfoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierInfoRepository supplierRepo;

    @Transactional
    public SupplierInfo createSupplier(SupplierInfoInput input) {
        log.info("Attempting to create supplier: name={}, type={}", input.getSupplierName(), input.getSupplierType());
        SupplierInfo supplier = SupplierMapper.toEntity(input);
        SupplierInfo saved = supplierRepo.save(supplier);
        log.info("Supplier created successfully with id={}", saved.getSupplierId());
        return saved;
    }

    @Transactional
    public SupplierInfo updateSupplier(Integer supplierId, SupplierInfoInput input) {
        log.info("Attempting full update for supplier with id={}", supplierId);
        SupplierInfo supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> {
                    log.error("Update failed: Supplier with id={} not found", supplierId);
                    return new SupplierNotFoundException("Supplier not found with id: " + supplierId);
                });
        SupplierMapper.updateEntity(input, supplier);
        SupplierInfo updated = supplierRepo.save(supplier);
        log.info("Supplier with id={} updated successfully", updated.getSupplierId());
        return updated;
    }

    @Transactional
    public SupplierInfo partialUpdateSupplier(Integer supplierId, SupplierInfoInput input) {
        log.info("Attempting partial update for supplier with id={}", supplierId);
        SupplierInfo supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> {
                    log.error("Partial update failed: Supplier with id={} not found", supplierId);
                    return new SupplierNotFoundException("Supplier not found with id: " + supplierId);
                });
        SupplierMapper.partialUpdateEntity(input, supplier);
        SupplierInfo updated = supplierRepo.save(supplier);
        log.info("Supplier with id={} partially updated successfully", updated.getSupplierId());
        return updated;
    }

    public SupplierInfo getSupplierById(Integer supplierId) {
        log.info("Fetching supplier with id={}", supplierId);
        SupplierInfo supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> {
                    log.warn("Fetch failed: Supplier with id={} not found", supplierId);
                    return new SupplierNotFoundException("Supplier not found with id: " + supplierId);
                });
        log.info("Supplier with id={} fetched successfully", supplierId);
        return supplier;
    }

    public List<SupplierInfo> listSuppliers() {
        log.info("Fetching all suppliers");
        List<SupplierInfo> suppliers = supplierRepo.findAll();
        log.info("Fetched {} suppliers", suppliers.size());
        return suppliers;
    }

    @Transactional
    public boolean deleteSupplier(Integer supplierId) {
        log.info("Attempting to delete supplier with id={}", supplierId);
        if (!supplierRepo.existsById(supplierId)) {
            log.warn("Delete failed: Supplier with id={} does not exist", supplierId);
            return false;
        }
        supplierRepo.deleteById(supplierId);
        log.info("Supplier with id={} deleted successfully", supplierId);
        return true;
    }
}