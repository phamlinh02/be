package com.maidoo.maidoo.web.rest;

import com.maidoo.maidoo.service.SupplierService;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import com.maidoo.maidoo.service.dto.SupplierDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/maidoo/supplier")
@AllArgsConstructor
@RestController
public class SupplierResource {

    private final SupplierService supplierService;

    @GetMapping("/get-all")
    @ApiOperation(value = "Lấy danh sách nhà cung cấp")
    public ResponseDTO getAll(
            @RequestParam(value = "name", defaultValue = "") String name
    ) {
        return ResponseDTO.success(this.supplierService.getAllSupplier(name));
    }

    @GetMapping("/get-supplier/{id}")
    @ApiOperation(value = "Tìm nhà cung cấp theo id")
    public ResponseDTO getSupplier(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseDTO.success(this.supplierService.getSupplier(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Thêm nhà cung cấp")
    public ResponseDTO saveSupplier(@Validated @RequestBody SupplierDTO supplierDTO) {
        return ResponseDTO.success(this.supplierService.saveSupplier(supplierDTO));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Cập nhập trạng thái nhà cung cấp")
    public ResponseDTO updateStatus(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseDTO.success(this.supplierService.updateStatus(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Xóa nhà cung cấp")
    public ResponseDTO deleteSupplier(
            @PathVariable(name = "id") Long id
    ) {
        this.supplierService.deleteSupplier(id);
        return ResponseDTO.success();
    }

}
