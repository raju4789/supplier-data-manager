package com.raju.supplier_data_manager.graphql;

import com.raju.supplier_data_manager.annotations.OnSupplierCreate;
import com.raju.supplier_data_manager.dtos.supplier.SupplierInfoInput;
import com.raju.supplier_data_manager.entities.SupplierInfo;
import com.raju.supplier_data_manager.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SupplierGraphQLResolver {

    private final SupplierService supplierService;

    @QueryMapping
    public SupplierInfo getSupplierById(@Argument Integer supplierId) {
        return supplierService.getSupplierById(supplierId);
    }

    @QueryMapping
    public List<SupplierInfo> listSuppliers() {
        return supplierService.listSuppliers();
    }

    @MutationMapping
    public SupplierInfo createSupplier(@Validated(OnSupplierCreate.class) @Argument SupplierInfoInput supplier) {
        return supplierService.createSupplier(supplier);
    }

    @MutationMapping
    public SupplierInfo updateSupplier(@Argument Integer supplierId, @Argument SupplierInfoInput supplier) {
        return supplierService.updateSupplier(supplierId, supplier);
    }

    @MutationMapping
    public SupplierInfo partialUpdateSupplier(@Argument Integer supplierId, @Argument SupplierInfoInput supplier) {
        return supplierService.partialUpdateSupplier(supplierId, supplier);
    }

    @MutationMapping
    public Boolean deleteSupplier(@Argument Integer supplierId) {
        return supplierService.deleteSupplier(supplierId);
    }
}