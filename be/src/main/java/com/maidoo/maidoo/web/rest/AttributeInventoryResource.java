package com.maidoo.maidoo.web.rest;


import com.maidoo.maidoo.service.AttributeInventoryService;
import com.maidoo.maidoo.service.dto.AttributeInventoryDTO;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maidoo/attribute-inventory")
@AllArgsConstructor
public class AttributeInventoryResource {

    private final AttributeInventoryService atributeInventoryService;


    @PostMapping()
    public ResponseDTO saveAtributeInventory(@RequestBody AttributeInventoryDTO dto) {
        return ResponseDTO.success(this.atributeInventoryService.saveAttributeInventory(dto));
    }

    @GetMapping("/{inventoryId}")
    public ResponseDTO getAttributeInventoryByInventoryId(@PathVariable Long inventoryId) {
        return ResponseDTO.success(this.atributeInventoryService.getAttributeInventoryByInventoryId(inventoryId));
    }
}
