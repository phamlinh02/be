package com.maidoo.maidoo.service;


import com.maidoo.maidoo.config.exception.common.BadRequestException;
import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.WishList;
import com.maidoo.maidoo.repository.IWishListRepository;
import com.maidoo.maidoo.service.dto.WishListDTO;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final IWishListRepository iWishListRepository;

    public WishListDTO saveItem(Long inventoryId, Long quantity) {
        if (quantity <= 0) {
            throw new BadRequestException("Quantity is not negative number");
        }
        WishList wishList = this.iWishListRepository.save(WishList
                .builder()
                .inventoryId(inventoryId)
                .quantity(quantity)
                .status(false)
                .build());
        return MapperUtils.map(wishList, WishListDTO.class);
    }

    public List<WishListDTO> saveItemList(List<WishListDTO> dtoList) {
        List<WishList> wishListList = new ArrayList<>();
        dtoList.forEach(dto -> {
            WishList wishList = WishList
                    .builder()
                    .inventoryId(dto.getInventoryId())
                    .quantity(dto.getQuantity())
                    .status(false)
                    .build();
            wishListList.add(wishList);
        });
        this.iWishListRepository.saveAll(wishListList);
        return MapperUtils.mapList(wishListList, WishListDTO.class);
    }

    public WishListDTO changeStatusItem(Long id) {
        WishList wishList = this.iWishListRepository.findById(id).orElseThrow(NotFoundException::new);
        if (wishList.getStatus() == null || wishList.getStatus() == false) {
            wishList.setStatus(true);
            return MapperUtils.map(wishList, WishListDTO.class);
        }
        wishList.setStatus(false);
        return MapperUtils.map(wishList, WishListDTO.class);
    }
}
