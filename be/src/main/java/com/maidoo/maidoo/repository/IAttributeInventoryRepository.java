package com.maidoo.maidoo.repository;

import com.maidoo.maidoo.domain.AttributeInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAttributeInventoryRepository extends JpaRepository<AttributeInventory, Long> {
    List<AttributeInventory> findByInventoryId(Long idInventory);


}
