package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {

}
