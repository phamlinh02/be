package com.maidoo.maidoo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maidoo.maidoo.config.Constant;
import com.maidoo.maidoo.domain.Bill;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
    // get bill by user id
    Page<Bill> findByUserId(Long id, Pageable pageable);

    // find bill by status
    @Query("SELECT b FROM Bill b WHERE b.status = :status")
    List<Bill> findByStatus(@Param("status") Constant.PAYMENT_STATUS status);
}
