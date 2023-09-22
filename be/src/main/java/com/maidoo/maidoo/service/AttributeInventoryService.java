package com.maidoo.maidoo.service;


import com.maidoo.maidoo.domain.Attribute;
import com.maidoo.maidoo.domain.AttributeInventory;
import com.maidoo.maidoo.repository.IAttributeInventoryRepository;
import com.maidoo.maidoo.repository.IAttributeRepository;
import com.maidoo.maidoo.service.dto.AttributeInventoryDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeInventoryService {

    private final IAttributeInventoryRepository iAttributeInventoryRepository;

    private final IAttributeRepository iAttributeRepository;

    public AttributeInventoryDTO saveAttributeInventory(AttributeInventoryDTO dto) {
        AttributeInventory attributeInventory = MapperUtils.map(dto, AttributeInventory.class);
        this.iAttributeInventoryRepository.save(attributeInventory);
        return MapperUtils.map(attributeInventory, AttributeInventoryDTO.class);
    }

    public List<AttributeInventoryDTO> getAttributeInventoryByInventoryId(Long inventoryId) {
        List<AttributeInventory> list = this.iAttributeInventoryRepository.findByInventoryId(inventoryId);
        List<AttributeInventoryDTO> dtoList = MapperUtils.mapList(list, AttributeInventoryDTO.class);
        dtoList.forEach(attributeInventory -> {
            Attribute attribute = this.iAttributeRepository.findById(attributeInventory.getAttributeId()).orElseThrow();
            attributeInventory.setNameAttribute(attribute.getName());
        });
        return dtoList;
    }
}
