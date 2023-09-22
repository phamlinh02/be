package com.maidoo.maidoo.repository;


import com.maidoo.maidoo.domain.Checksheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICheckSheetRepository extends JpaRepository<Checksheet, Long> {
}
