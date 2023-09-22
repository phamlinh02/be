package com.maidoo.maidoo.service.dto;


import com.maidoo.maidoo.domain.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChecksheetDTO {
    private Long id;
    private Long inventoryId;
    private Long quantity;
    private Long quantityReal;
    private Long userId;
    private String note;
    private String nameInventory;
    private String nameCategory;
    private List<Attribute> attributeList;
}
