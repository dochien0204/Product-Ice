package com.example.kiemtra.service;


import com.example.kiemtra.dto.ProductDTO;
import com.example.kiemtra.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();

    Optional<Product> findProductById(Long productId);

    void createNewProductByCategory(Long categoryId, ProductDTO productDTO);

    List<Product> findProductBySubString(String string);

    List<Product> findPopularProducts(Integer number);

    void deleteProductById(Long productId);

    Product updateProductById(Long productId, ProductDTO productDTO);

    void addProductIntoCart(Long productId, Long cartId);

    void removeProductfromCart(Long productId, Long cartId);

}
