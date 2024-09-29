package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartIdAndCoffeeId(Long cartId, Long coffeeId);
}
