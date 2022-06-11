package com.example.kiemtra.service;

import com.example.kiemtra.entity.Cart;

import java.util.List;

public interface CartService {
    void addCart();

    List<Cart> viewAllcart();
}
