package com.maidoo.maidoo.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListDTO {
    private Long id;
    private Long inventoryId;
    private Long quantity;
    private Boolean status;
}
