package com.maidoo.maidoo.service.dto.bill;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.maidoo.maidoo.config.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDto {
    private Long id;
    private Long billId;
    private Long inventoryId;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private Constant.ORDER_TYPE type;
}
