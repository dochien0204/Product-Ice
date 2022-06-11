package com.example.kiemtra.controller;

import com.example.kiemtra.dto.DiscountDTO;
import com.example.kiemtra.service.DiscountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping("/get")
    @ApiOperation(value = "Lấy ra tất cả vé giảm giá của cửa hàng")
    public ResponseEntity<?> getAllDiscount()
    {
        return ResponseEntity.status(200).body(discountService.getAllDiscount());
    }

    @PostMapping("/add-discount")
    @ApiOperation(value = "Thêm vé giảm giá cho cửa hàng", response = ResponseEntity.class)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    private ResponseEntity<?> createNewDiscount(@RequestBody DiscountDTO discountDTO)
    {
        discountService.addDiscount(discountDTO);
        return ResponseEntity.status(200).body("Add discount thành công");
    }

    @PatchMapping("/update-discount/{discountId}")
    @ApiOperation(value = "Sửa thông tin cho vé giảm giá")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    private ResponseEntity<?> updateDiscount(@RequestBody DiscountDTO discountDTO,
                                             @PathVariable Long discountId)
    {
        discountService.updateDiscountById(discountId, discountDTO);
        return ResponseEntity.status(200).body("Sửa thông tin vé giảm giá thành công");
    }

    @DeleteMapping("/delete-discount/{discountId}")
    @ApiOperation(value = "Xóa vé giảm giá của cửa hàng", response = ResponseEntity.class)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteDiscount(@PathVariable Long discountId)
    {
        discountService.deleteDiscountById(discountId);
        return ResponseEntity.status(200).body("Xóa vé giảm giá thành công");
    }
}
