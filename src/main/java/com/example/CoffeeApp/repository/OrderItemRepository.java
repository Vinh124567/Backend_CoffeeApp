package com.example.CoffeeApp.repository;
import com.example.CoffeeApp.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
