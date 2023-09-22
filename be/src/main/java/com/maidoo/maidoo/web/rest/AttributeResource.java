package com.maidoo.maidoo.web.rest;

import com.maidoo.maidoo.service.AttributeService;
import com.maidoo.maidoo.service.dto.AttributeDTO;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/maidoo/attribute")
@AllArgsConstructor
@RestController
public class AttributeResource {
    private final AttributeService attributeService;

    @GetMapping("/get-all")
    @ApiOperation(value = "Lấy danh sách thuộc tính")
    public ResponseDTO getAll(
            @RequestParam(value = "name",defaultValue = "") String name
    ) {
       return ResponseDTO.success(this.attributeService.getAllAttribute(name));
    }

    @GetMapping("/get-attribute/{id}")
    @ApiOperation(value = "Tìm thuộc tính theo id")
    public ResponseDTO getAttribute(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseDTO.success(this.attributeService.getAttribute(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Thêm thuộc tính")
    public ResponseDTO saveAttribute(@Validated @RequestBody AttributeDTO attributeDTO) {
        return ResponseDTO.success(this.attributeService.saveAttribute(attributeDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Xóa thuộc tính")
    public ResponseDTO deleteAttribute(@PathVariable(name = "id") Long id) {
        this.attributeService.deleteAttribute(id);
        return ResponseDTO.success();
    }

}

