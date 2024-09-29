package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserId(String userId);

}
