package com.raju.supplier_data_manager.controllers;

import com.raju.supplier_data_manager.dtos.common.CommonApiResponse;
import com.raju.supplier_data_manager.dtos.supplier.SupplierInfoInput;
import com.raju.supplier_data_manager.entities.SupplierInfo;
import com.raju.supplier_data_manager.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierDataController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<CommonApiResponse<SupplierInfo>> createSupplier(
            @RequestBody @Validated SupplierInfoInput input) {
        SupplierInfo created = supplierService.createSupplier(input);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonApiResponse<>(created));
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<CommonApiResponse<SupplierInfo>> getSupplier(@PathVariable Integer supplierId) {
        SupplierInfo supplier = supplierService.getSupplierById(supplierId);
        return ResponseEntity.ok(new CommonApiResponse<>(supplier));
    }

    @GetMapping
    public ResponseEntity<CommonApiResponse<List<SupplierInfo>>> listSuppliers() {
        List<SupplierInfo> suppliers = supplierService.listSuppliers();
        return ResponseEntity.ok(new CommonApiResponse<>(suppliers));
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<CommonApiResponse<SupplierInfo>> updateSupplier(
            @PathVariable Integer supplierId,
            @RequestBody SupplierInfoInput input) {
        SupplierInfo updated = supplierService.updateSupplier(supplierId, input);
        return ResponseEntity.ok(new CommonApiResponse<>(updated));
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity<CommonApiResponse<SupplierInfo>> partialUpdateSupplier(
            @PathVariable Integer supplierId,
            @RequestBody SupplierInfoInput input) {
        SupplierInfo updated = supplierService.partialUpdateSupplier(supplierId, input);
        return ResponseEntity.ok(new CommonApiResponse<>(updated));
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<CommonApiResponse<Boolean>> deleteSupplier(@PathVariable Integer supplierId) {
        boolean deleted = supplierService.deleteSupplier(supplierId);
        return ResponseEntity.ok(new CommonApiResponse<>(deleted));
    }
}