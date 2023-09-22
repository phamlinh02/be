package com.maidoo.maidoo.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeInventoryDTO {

    private Long id;
    private Long attributeId;
    private Long inventoryId;
    private String value;
    private String nameAttribute;
    private String nameInventory;
}
