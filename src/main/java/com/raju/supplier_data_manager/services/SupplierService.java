package com.raju.supplier_data_manager.services;

import com.raju.supplier_data_manager.dtos.supplier.SupplierInfoInput;
import com.raju.supplier_data_manager.entities.SupplierInfo;

import java.util.List;

public interface SupplierService {

    SupplierInfo createSupplier(SupplierInfoInput input);

    SupplierInfo updateSupplier(Integer supplierId, SupplierInfoInput input);

    SupplierInfo partialUpdateSupplier(Integer supplierId, SupplierInfoInput input);

    SupplierInfo getSupplierById(Integer supplierId);

    List<SupplierInfo> listSuppliers();

    boolean deleteSupplier(Integer supplierId);
}
