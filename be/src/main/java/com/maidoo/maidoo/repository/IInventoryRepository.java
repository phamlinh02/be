package com.maidoo.maidoo.repository;

import com.maidoo.maidoo.domain.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryRepository extends JpaRepository<Inventory,Long> {
    Page<Inventory> findAllByNameContains(String name, Pageable pageable);
}
