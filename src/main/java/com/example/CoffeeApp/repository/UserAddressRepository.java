package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUser_Id(String userId);
}

