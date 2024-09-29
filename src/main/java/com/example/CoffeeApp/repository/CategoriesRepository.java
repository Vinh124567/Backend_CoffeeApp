package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.CoffeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CoffeeCategory, String> {
}
