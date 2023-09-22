package com.maidoo.maidoo.repository;

import com.maidoo.maidoo.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWishListRepository extends JpaRepository<WishList, Long> {
}
