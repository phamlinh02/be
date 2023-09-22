package com.maidoo.maidoo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.maidoo.maidoo.domain.Bill;
import com.maidoo.maidoo.domain.BillDetail;
import com.maidoo.maidoo.repository.IBillDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillDetailService {

    private final IBillDetailRepository billDetailRepository;

    // create save list bill detail
    public List<BillDetail> createBillDetail(List<BillDetail> billDetails) {
        return this.billDetailRepository.saveAll(billDetails);
    }

    // get list bill detail by bill id
    public List<BillDetail> getBillDetailByBillId(Long id) {
        return this.billDetailRepository.findByBillId(id);
    }

    // import bill detail
    public List<BillDetail> addItemBillDetails(List<BillDetail> billDetails) {
        return this.billDetailRepository.saveAll(billDetails);
    }

}
