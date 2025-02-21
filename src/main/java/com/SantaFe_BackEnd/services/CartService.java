package com.SantaFe_BackEnd.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SantaFe_BackEnd.models.ICartItem;
import com.SantaFe_BackEnd.repositories.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public ICartItem addToCart(ICartItem item) {
        return cartRepository.save(item);
    }

    public List<ICartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }
}
