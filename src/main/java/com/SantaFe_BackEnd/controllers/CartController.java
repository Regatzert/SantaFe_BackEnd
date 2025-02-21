package com.SantaFe_BackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SantaFe_BackEnd.models.ICartItem;
import com.SantaFe_BackEnd.services.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @PostMapping("/add")
    public ICartItem addToCart(@RequestBody ICartItem item) {
        return cartService.addToCart(item);
    }

    @GetMapping("/items")
    public List<ICartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }
}
