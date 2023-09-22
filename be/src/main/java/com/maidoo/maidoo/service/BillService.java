package com.maidoo.maidoo.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.maidoo.maidoo.config.Constant;
import com.maidoo.maidoo.config.Constant.ROLE_USER;
import com.maidoo.maidoo.config.exception.common.NotFoundException;
import com.maidoo.maidoo.domain.Bill;
import com.maidoo.maidoo.domain.BillDetail;
import com.maidoo.maidoo.domain.User;
import com.maidoo.maidoo.repository.IBillRepository;
import com.maidoo.maidoo.service.dto.bill.BillDetailDto;
import com.maidoo.maidoo.service.dto.bill.BillDto;
import com.maidoo.maidoo.service.mapper.MapperUtils;
import com.maidoo.maidoo.service.util.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillDetailService billDetailService;
    private final IBillRepository billRepository;
    private final CommonService commonService;
    private final InventoryService inventoryService;
    

    @Transactional
    public BillDto createBill(BillDto dto) {
        User user = this.commonService.getCurrentUser();
        Bill bill = MapperUtils.map(dto, Bill.class);
        // set id user for bill
        bill.setUserId(user.getId());
        // save bill
        Long idBill = this.billRepository.save(bill).getId();

        List<BillDetail> billDetails = MapperUtils.mapList(dto.getBillDetails(), BillDetail.class);
        // set id bill for bill detail
        billDetails.forEach(billDetail -> billDetail.setBillId(idBill));
        // save bill detail
        billDetails = this.billDetailService.createBillDetail(billDetails);


        // tạo bill là trừ hoặc cộng luôn trong inventory
        dto.getBillDetails().forEach(billDetail -> {
            if (billDetail.getType().equals(Constant.ORDER_TYPE.IMPORT)) {
                this.inventoryService.importToInventory(billDetail.getInventoryId(), billDetail.getQuantity());
            } else {
                this.inventoryService.exportFromInventory(billDetail.getInventoryId(), billDetail.getQuantity());
            }    
        });
        
        dto.setId(idBill);
        dto.setBillDetails(MapperUtils.mapList(billDetails, BillDetailDto.class));

        return dto;
    }

    // // Schedule an amount deduction if the invoice is 10 days past due when the invoice was created and the amount is unpaid
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleBill() {
        List<Bill> bills = this.billRepository.findByStatus(Constant.PAYMENT_STATUS.PENDING);
        bills.forEach(bill -> {
            if (bill.getCreatedTimestamp().plusSeconds(10 * 24 * 60 * 60).isBefore(Instant.now())) {
                this.billDetailService.getBillDetailByBillId(bill.getId()).forEach(billDetail -> {
                    if (billDetail.getType().equals(Constant.ORDER_TYPE.IMPORT)) {
                        this.inventoryService.exportFromInventory(billDetail.getInventoryId(), billDetail.getQuantity());
                    } else {
                        this.inventoryService.importToInventory(billDetail.getInventoryId(), billDetail.getQuantity());
                    }
                });
                bill.setStatus(Constant.PAYMENT_STATUS.CANCEL);
                this.billRepository.save(bill);
            }
        });
    }

    // Get all bill and paging
    public Page<BillDto> getAllBill(Pageable pageable) {
        Page<Bill> page = this.billRepository.findAll(pageable);
        return page.map(bill -> MapperUtils.map(bill, BillDto.class));
    }
    // Get bill by user id
    public Page<BillDto> getBillByUserId(Pageable pageable) {
        User user = this.commonService.getCurrentUser();
        Page<Bill> page = this.billRepository.findByUserId(user.getId(), pageable);
        return page.map(bill -> MapperUtils.map(bill, BillDto.class));
    }

    public BillDto getBillById(Long id) {
        Bill bill = this.billRepository.findById(id).orElseThrow(NotFoundException::new);
        BillDto dto = MapperUtils.map(bill, BillDto.class);
        List<BillDetail> billDetails = this.billDetailService.getBillDetailByBillId(id);
        dto.setBillDetails(MapperUtils.mapList(billDetails, BillDetailDto.class));
        return dto;
    }

    public BillDto addItemBillDetails(List<BillDetailDto> billDetailsDto, Long idBill) {
        User user = this.commonService.getCurrentUser();
        
        Bill bill = this.billRepository.findById(idBill).orElseThrow(NotFoundException::new);
        if (bill.getUserId() != user.getId() && user.getRole() != ROLE_USER.ADMIN) {
            throw new NotFoundException("Bạn không có quyền truy cập hóa đơn này");
        }
        // set id bill for bill detail and covert to bill detail
        billDetailsDto.forEach(billDetail -> billDetail.setBillId(idBill));
        List<BillDetail> billDetails = MapperUtils.mapList(billDetailsDto, BillDetail.class);

        // addItemBillDetails
        billDetails = this.billDetailService.addItemBillDetails(billDetails);

        // update total quantity and total price
        Long totalQuantity = billDetails.stream().map(BillDetail::getQuantity).reduce(0L, Long::sum);
        BigDecimal totalPrice = billDetails.stream().map(BillDetail::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        bill.setTotalQuantity(totalQuantity);
        bill.setTotalPrice(totalPrice);
        bill = this.billRepository.save(bill);

        BillDto dto = MapperUtils.map(bill, BillDto.class);
        dto.setBillDetails(MapperUtils.mapList(billDetails, BillDetailDto.class));
        return dto;
    }
}
