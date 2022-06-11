package com.example.kiemtra.service.Impl;

import com.example.kiemtra.entity.Cart;
import com.example.kiemtra.entity.User;
import com.example.kiemtra.repostitory.CartRepository;
import com.example.kiemtra.repostitory.UserRepository;
import com.example.kiemtra.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> viewAllcart() {
        return cartRepository.findAll();
    }
}
