package com.example.kiemtra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String image;

    private String productName;

    private String SKU;

    private String describe;

    private String price;

    private int purchases;
}
