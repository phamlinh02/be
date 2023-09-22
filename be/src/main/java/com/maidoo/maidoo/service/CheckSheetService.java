package com.maidoo.maidoo.service;


import com.maidoo.maidoo.domain.*;
import com.maidoo.maidoo.repository.*;
import com.maidoo.maidoo.service.dto.ChecksheetDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import com.maidoo.maidoo.service.util.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CheckSheetService {
    private final ICheckSheetRepository iCheckSheetRepository;

    private final IInventoryRepository inventoryRepository;

    private final ICategoryRepository iCategoryRepository;

    private final IAttributeInventoryRepository iAttributeInventoryRepository;

    private final IAttributeRepository iAttributeRepository;


    private final CommonService commonService;

    public ChecksheetDTO saveCheckSheet(ChecksheetDTO dto) {
        User user = this.commonService.getCurrentUser();
        Inventory inventory = this.inventoryRepository.findById(dto.getInventoryId()).orElseThrow();
        Category category = this.iCategoryRepository.findById(inventory.getCategoryId()).orElseThrow();
        Checksheet checksheet = this.iCheckSheetRepository.save(Checksheet
                .builder()
                .inventoryId(dto.getInventoryId())
                .quantity(inventory.getQuantity())
                .quantityReal(dto.getQuantityReal())
                .userId(user.getId())
                .note(dto.getNote())
                .build());
        inventory.setQuantity(dto.getQuantityReal());
        this.inventoryRepository.save(inventory);
        ChecksheetDTO checksheetDTO = MapperUtils.map(checksheet, ChecksheetDTO.class);
        checksheetDTO.setNameInventory(inventory.getName());
        checksheetDTO.setNameCategory(category.getName());
        List<AttributeInventory> attributeInventoryList = this.iAttributeInventoryRepository.findByInventoryId(dto.getInventoryId());
        List<Attribute> attributeList = new ArrayList<>();
        attributeInventoryList.forEach(attributeInventory -> {
            Attribute attribute = this.iAttributeRepository.findById(attributeInventory.getAttributeId()).orElseThrow();
            attributeList.add(attribute);
        });
        checksheetDTO.setAttributeList(attributeList);
        return checksheetDTO;
    }

    public List<ChecksheetDTO> saveCheckSheetList(List<ChecksheetDTO> dtoList) {
        User user = this.commonService.getCurrentUser();
        List<Checksheet> checksheetList = new ArrayList<>();
        List<Inventory> inventoryList = new ArrayList<>();
        List<ChecksheetDTO> checksheetDTOList = new ArrayList<>();
        dtoList.forEach(dto -> {
            Inventory inventory = this.inventoryRepository.findById(dto.getInventoryId()).orElseThrow();
            Category category = this.iCategoryRepository.findById(inventory.getCategoryId()).orElseThrow();
            Checksheet checksheet = Checksheet
                    .builder()
                    .inventoryId(dto.getInventoryId())
                    .quantity(inventory.getQuantity())
                    .quantityReal(dto.getQuantityReal())
                    .userId(user.getId())
                    .note(dto.getNote())
                    .build();
            checksheetList.add(checksheet);
            inventory.setQuantity(dto.getQuantityReal());
            inventoryList.add(inventory);
            ChecksheetDTO checksheetDTO = MapperUtils.map(checksheet, ChecksheetDTO.class);
            checksheetDTO.setNameInventory(inventory.getName());
            checksheetDTO.setNameCategory(category.getName());
            List<AttributeInventory> attributeInventoryList = this.iAttributeInventoryRepository.findByInventoryId(dto.getInventoryId());
            List<Attribute> attributeList = new ArrayList<>();
            attributeInventoryList.forEach(attributeInventory -> {
                Attribute attribute = this.iAttributeRepository.findById(attributeInventory.getAttributeId()).orElseThrow();
                attributeList.add(attribute);
            });
            checksheetDTO.setAttributeList(attributeList);
            checksheetDTOList.add(checksheetDTO);
        });
        this.iCheckSheetRepository.saveAll(checksheetList);
        this.inventoryRepository.saveAll(inventoryList);
        return checksheetDTOList;
    }
}
