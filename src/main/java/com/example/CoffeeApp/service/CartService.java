package com.example.CoffeeApp.service;

import com.example.CoffeeApp.DTO.CartDTO.CartResponseDTO;
import com.example.CoffeeApp.DTO.CartDTO.CartItemCreateDTO;
import com.example.CoffeeApp.Entity.Cart;
import com.example.CoffeeApp.Entity.CartItem;

public interface CartService {
    public String createCart(Cart cart);
    public  void removeCartItem(Long itemId);
    public CartItem addItemToCart(CartItemCreateDTO cartItemCreateDTO);
    public CartResponseDTO findByUserId(String userId);


}
