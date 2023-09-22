package com.maidoo.maidoo.web.rest;


import com.maidoo.maidoo.service.WishListService;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import com.maidoo.maidoo.service.dto.WishListDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maidoo/wish-list")
@AllArgsConstructor
public class WishlistResource {

    private final WishListService wishListService;

    @PostMapping()
    public ResponseDTO saveItem(@RequestBody WishListDTO dto) {
        return ResponseDTO.success(this.wishListService.saveItem(dto.getInventoryId(), dto.getQuantity()));
    }

    @PostMapping("/save-wishlist")
    public ResponseDTO saveItemList(@RequestBody List<WishListDTO> dtoList) {
        return ResponseDTO.success(this.wishListService.saveItemList(dtoList));
    }

    @GetMapping("/{id}")
    public ResponseDTO changeStatusItem(@PathVariable Long id) {

        return ResponseDTO.success(this.wishListService.changeStatusItem(id));
    }
}
