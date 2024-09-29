package com.example.CoffeeApp.repository;
import com.example.CoffeeApp.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

