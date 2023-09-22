package com.maidoo.maidoo.repository;

import com.maidoo.maidoo.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttributeRepository extends JpaRepository<Attribute,Long> {
    List<Attribute> getAllAttributeByNameContains(String name);

    Attribute findFirstByName(String name);

}
