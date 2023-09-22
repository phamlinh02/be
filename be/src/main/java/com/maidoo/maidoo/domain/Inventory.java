package com.maidoo.maidoo.domain;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends SharedAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal priceImport;
    private BigDecimal priceExport;
    private Long quantity;
    private String image;
    private Boolean maskDelete;
    private Long categoryId;
    private Long supplierId;
    private Long minQuantity;
    private String code;
}
