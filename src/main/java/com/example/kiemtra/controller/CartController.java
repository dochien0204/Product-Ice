package com.example.kiemtra.controller;

import com.example.kiemtra.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-cart")
    public ResponseEntity<?> addCart()
    {
        cartService.addCart();
        return ResponseEntity.status(200).body("Add cart thành công");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCart()
    {
        return ResponseEntity.ok(cartService.viewAllcart());
    }

}
