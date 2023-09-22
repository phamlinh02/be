package com.maidoo.maidoo.web.rest;


import com.maidoo.maidoo.service.CategoryService;
import com.maidoo.maidoo.service.dto.CategoryDTO;
import com.maidoo.maidoo.service.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maidoo/category")
@AllArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseDTO getAllCategory() {
        return ResponseDTO.success(this.categoryService.getAllCategory());
    }

    @PostMapping()
    public ResponseDTO saveCategory(@RequestBody CategoryDTO dto) {

        return ResponseDTO.success(this.categoryService.saveCategory(dto));
    }

    @GetMapping("/{id}")
    public ResponseDTO getCategoryId(@PathVariable Long id) {

        return ResponseDTO.success(this.categoryService.getCategory(id));
    }

}
