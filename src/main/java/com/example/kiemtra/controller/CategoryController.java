package com.example.kiemtra.controller;


import com.example.kiemtra.base.VsResponseUtil;
import com.example.kiemtra.dto.CategoryDTO;
import com.example.kiemtra.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Lấy ra tất cả loại sản phẩm" , response = ResponseEntity.class)
    public ResponseEntity<?> getAllCategories()
    {
        return VsResponseUtil.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{categoryId}")
    @ApiOperation(value = "Lấy ra thông tin loại sản phẩm theo Id")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId)
    {
        return VsResponseUtil.ok(categoryService.findCategoryById(categoryId));
    }

    @GetMapping("/list/{categoryId}")
    @ApiOperation(value = "Lấy ra danh sách sản phẩm theo loại sản phẩm", response = ResponseEntity.class)
    public ResponseEntity<?> getListProductByCategoryId(@PathVariable(name = "categoryId") Long categoryId)
    {
        return VsResponseUtil.ok(categoryService.getListProductByCategoryId(categoryId));
    }

    @PostMapping("/add-categories")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Thêm loại sản phẩm")
    public ResponseEntity<?> createNewCategories(@RequestBody CategoryDTO categoryDTO)
    {
        categoryService.createNewCategory(categoryDTO);
        return ResponseEntity.status(200).body("Tạo loại sản phẩm mới thành công");
    }

}
