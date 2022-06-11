package com.example.kiemtra.service;

import com.example.kiemtra.dto.DiscountDTO;
import com.example.kiemtra.entity.Discount;

import java.util.List;

public interface DiscountService{

    List<Discount> getAllDiscount();

    void addDiscount(DiscountDTO discountDTO);

    void updateDiscountById(Long discountId ,DiscountDTO discountDTO);

    void deleteDiscountById(Long discountId);
}
