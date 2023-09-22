package com.maidoo.maidoo.domain;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.maidoo.maidoo.config.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill extends SharedAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalQuantity;
    private Long supplierId;
    @Enumerated(EnumType.STRING)
    private Constant.ORDER_TYPE type;
    private Constant.PAYMENT_STATUS status;
}
