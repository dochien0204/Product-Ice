package com.example.kiemtra.util;

import com.example.kiemtra.dto.ProductDTO;
import com.example.kiemtra.entity.Product;
import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjectUtil {

    @Autowired
    private static ModelMapper modelMapper;

    @Autowired
    private static Slugify slugify;

    public static Product convertProduct(Product product, ProductDTO productDTO)
    {
        Product newProduct;
        if(product == null)
            newProduct = modelMapper.map(productDTO,Product.class);
        else {
            newProduct = product;
            newProduct.setProductName(productDTO.getProductName());
        }
        newProduct.setSlug(slugify.slugify(newProduct.getProductName()));
        return newProduct;
    }
}
