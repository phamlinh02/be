package com.maidoo.maidoo.service.dto.bill;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.maidoo.maidoo.config.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long id;
    private String code;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalQuantity;
    private Long supplierId;
    @Enumerated(EnumType.STRING)
    private Constant.ORDER_TYPE type;
    private Constant.PAYMENT_STATUS status;
    private List<BillDetailDto> billDetails;
}
