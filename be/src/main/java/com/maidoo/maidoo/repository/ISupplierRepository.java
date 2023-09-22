package com.maidoo.maidoo.repository;

import com.maidoo.maidoo.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByNameContains(String name);
}
