package com.maidoo.maidoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maidoo.maidoo.domain.BillDetail;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
    List<BillDetail> findByBillId(Long id);

    // find bill detail by bill id and inventory id
    BillDetail findByBillIdAndInventoryId(Long billId, Long inventoryId);
    
}
