package com.maidoo.maidoo.web.rest;

import com.maidoo.maidoo.service.InventoryService;
import com.maidoo.maidoo.service.dto.InventoryDTO;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/maidoo/inventory")
@RestController
public class InventoryResource {

    private final InventoryService inventoryService;

    @GetMapping("/get-all")
    @ApiOperation(value = "Lấy danh sách linh kiện")
    public ResponseDTO getAllInventory(
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable
    ) {
        return ResponseDTO.success(this.inventoryService.getAllInventory(name, pageable));
    }

    @GetMapping("/get-inventory/{id}")
    @ApiOperation(value = "Tìm linh kiện theo id")
    public ResponseDTO getInventory(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseDTO.success(this.inventoryService.getInventory(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Thêm linh kiện")
    public ResponseDTO saveInventory(@Validated @RequestBody InventoryDTO inventoryDTO) {
        return ResponseDTO.success(this.inventoryService.saveInventory(inventoryDTO));
    }

    @PostMapping("/import-to-inventory")
    @ApiOperation(value = "Nhập số lượng linh kiện")
    public ResponseDTO importToInventory(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "quantity") Long quantity
    ) {
        return ResponseDTO.success(this.inventoryService.importToInventory(id, quantity));
    }

    @PostMapping("/export-to-inventory")
    @ApiOperation(value = "Xuất số lượng linh kiện")
    public ResponseDTO exportFromInventory(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "quantity") Long quantity
    ) {
        return ResponseDTO.success(this.inventoryService.exportFromInventory(id, quantity));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Cập nhật trạng thái linh kiện")
    public ResponseDTO updateStatus(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseDTO.success(this.inventoryService.updateStatus(id));
    }

}
