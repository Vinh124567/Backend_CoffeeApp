package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.OrderVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderVoucherRepository extends JpaRepository<OrderVoucher, Long> {
}

