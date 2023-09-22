package com.maidoo.maidoo.service;

import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.Supplier;
import com.maidoo.maidoo.repository.ISupplierRepository;
import com.maidoo.maidoo.service.dto.SupplierDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {

    private final ISupplierRepository supplierRepository;

    public List<SupplierDTO> getAllSupplier(String name) {
        return MapperUtils.mapList(this.supplierRepository.findAllByNameContains(name), SupplierDTO.class);
    }

    public SupplierDTO getSupplier(Long id) {
        return MapperUtils.map(this.supplierRepository.findById(id).orElseThrow(NotFoundException::new), SupplierDTO.class);
    }

    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = MapperUtils.map(supplierDTO, Supplier.class);
        this.supplierRepository.save(supplier);
        return supplierDTO;
    }

    public SupplierDTO updateStatus(Long id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(NotFoundException::new);
        if (supplier.getMaskDelete() == null || supplier.getMaskDelete() == true) {
            supplier.setMaskDelete(false);
            return MapperUtils.map(this.supplierRepository.save(supplier), SupplierDTO.class);
        }
        supplier.setMaskDelete(true);
        return MapperUtils.map(this.supplierRepository.save(supplier), SupplierDTO.class);
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(NotFoundException::new);
        this.supplierRepository.deleteById(supplier.getId());
    }

}
