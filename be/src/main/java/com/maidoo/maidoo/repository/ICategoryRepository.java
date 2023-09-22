package com.maidoo.maidoo.repository;

import com.maidoo.maidoo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
