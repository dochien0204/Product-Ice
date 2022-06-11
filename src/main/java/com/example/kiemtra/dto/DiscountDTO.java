package com.example.kiemtra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountDTO {
    private String discountName;

    private String describe;

    private String discountPercent;

}
