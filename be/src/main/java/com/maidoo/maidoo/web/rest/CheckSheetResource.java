package com.maidoo.maidoo.web.rest;


import com.maidoo.maidoo.service.CheckSheetService;
import com.maidoo.maidoo.service.dto.ChecksheetDTO;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/maidoo/check-sheet")
@AllArgsConstructor
@RestController
public class CheckSheetResource {

    private final CheckSheetService checkSheetService;

    @PostMapping("/save")
    public ResponseDTO saveCheckSheet(@RequestBody ChecksheetDTO dto) {
        return ResponseDTO.success(this.checkSheetService.saveCheckSheet(dto));
    }

    @PostMapping("/save-list")
    public ResponseDTO saveCheckSheetList(@RequestBody List<ChecksheetDTO> dtoList) {
        return ResponseDTO.success(this.checkSheetService.saveCheckSheetList(dtoList));
    }
}
