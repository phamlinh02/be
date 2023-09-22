package com.maidoo.maidoo.web.rest;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maidoo.maidoo.service.BillService;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import com.maidoo.maidoo.service.dto.bill.BillDetailDto;
import com.maidoo.maidoo.service.dto.bill.BillDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maidoo/bill")
@RequiredArgsConstructor
public class BillResoure {

    private final BillService billService;

    @PostMapping("/save")
    @ApiOperation(value = "Thêm hóa đơn")
    public ResponseDTO save(@RequestBody BillDto dto) {
        return ResponseDTO.success(this.billService.createBill(dto));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Lấy thông tin hóa đơn")
    public ResponseDTO get(@PathVariable Long id) {
        return ResponseDTO.success(this.billService.getBillById(id));
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Lấy danh sách hóa đơn")
    public ResponseDTO getAll(Pageable pageable) {
        return ResponseDTO.success(this.billService.getAllBill(pageable));
    }

    @GetMapping("/get-by-user")
    @ApiOperation(value = "Lấy danh sách hóa đơn theo user")
    public ResponseDTO getByUser(Pageable pageable) {
        return ResponseDTO.success(this.billService.getBillByUserId(pageable));
    }

    @PostMapping("/add-item/{idBill}")
    @ApiOperation(value = "Nhập thêm hóa đơn")
    public ResponseDTO importBill(@RequestBody List<BillDetailDto> billDetails, @PathVariable Long idBill) {
        return ResponseDTO.success(this.billService.addItemBillDetails(billDetails, idBill));
    }
}
