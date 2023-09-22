package com.maidoo.maidoo.service;

import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.Attribute;
import com.maidoo.maidoo.domain.AttributeInventory;
import com.maidoo.maidoo.domain.Inventory;
import com.maidoo.maidoo.repository.IAttributeInventoryRepository;
import com.maidoo.maidoo.repository.IAttributeRepository;
import com.maidoo.maidoo.repository.IInventoryRepository;
import com.maidoo.maidoo.service.dto.AttributeDTO;
import com.maidoo.maidoo.service.dto.InventoryDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class InventoryService {

    private final IInventoryRepository inventoryRepository;

    private final IAttributeRepository attributeRepository;

    private final IAttributeInventoryRepository attributeInventoryRepository;

    public Page<InventoryDTO> getAllInventory(String name, Pageable pageable) {
        return MapperUtils.mapEntityPageIntoDtoPage(this.inventoryRepository.findAllByNameContains(name, pageable), InventoryDTO.class);
    }

    public InventoryDTO getInventory(Long id) {
        return MapperUtils.map(this.inventoryRepository.findById(id).orElseThrow(NotFoundException::new), InventoryDTO.class);
    }

    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {
        List<AttributeDTO> attributeDTOS = Lists.newArrayList();
        Inventory inventory = this.inventoryRepository.save(MapperUtils.map(inventoryDTO, Inventory.class));
        inventoryDTO.getAttributes().stream().map(
                attributeDTO -> {
                    Attribute firstByName = this.attributeRepository.findFirstByName(attributeDTO.getName());
                    if (firstByName == null) {
                        attributeDTOS.add(attributeDTO);
                        Attribute save = this.attributeRepository.save(MapperUtils.map(attributeDTO, Attribute.class));
                        this.attributeInventoryRepository.save(
                                AttributeInventory.builder()
                                        .inventoryId(inventory.getId())
                                        .attributeId(save.getId())
                                        .value(attributeDTO.getValue())
                                        .build()
                        );
                    } else {
                        this.attributeInventoryRepository.save(
                                AttributeInventory.builder()
                                        .inventoryId(inventory.getId())
                                        .attributeId(firstByName.getId())
                                        .value(attributeDTO.getValue())
                                        .build()
                        );
                    }
                    return null;
                }
        ).collect(Collectors.toList());
        return inventoryDTO;
    }

    public InventoryDTO importToInventory(Long id, Long quantity) {
        Inventory inventory = this.inventoryRepository.findById(id).orElseThrow(NotFoundException::new);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return MapperUtils.map(this.inventoryRepository.save(inventory), InventoryDTO.class);
    }

    public InventoryDTO exportFromInventory(Long id, Long quantity) {
        Inventory inventory = this.inventoryRepository.findById(id).orElseThrow(NotFoundException::new);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return MapperUtils.map(this.inventoryRepository.save(inventory), InventoryDTO.class);
    }

    public InventoryDTO updateStatus(Long id) {
        Inventory inventory = this.inventoryRepository.findById(id).orElseThrow(NotFoundException::new);
        if (inventory.getMaskDelete() == null || inventory.getMaskDelete() == true) {
            inventory.setMaskDelete(false);
            return MapperUtils.map(this.inventoryRepository.save(inventory), InventoryDTO.class);
        }
        inventory.setMaskDelete(true);
        return MapperUtils.map(this.inventoryRepository.save(inventory), InventoryDTO.class);
    }

    //TODO: Schedule reviewInventory lọc linh kiện nào cần lấy

}
