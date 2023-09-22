package com.maidoo.maidoo.service.dto;

import com.maidoo.maidoo.domain.AttributeInventory;
import com.maidoo.maidoo.service.util.validate.Add;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {
    private Long id;
    @NotNull(groups = Add.class)
    private String name;
    private String description;
    @NotNull(groups = Add.class)
    private BigDecimal priceImport;
    @NotNull(groups = Add.class)
    private BigDecimal priceExport;
    @NotNull(groups = Add.class)
    private Long quantity;
    private String image;
    private Boolean maskDelete;
    @NotNull(groups = Add.class)
    private Long categoryId;
    @NotNull(groups = Add.class)
    private Long supplierId;
    @NotNull(groups = Add.class)
    private Long minQuantity;
    private String code;
    private List<AttributeDTO> attributes;
}
