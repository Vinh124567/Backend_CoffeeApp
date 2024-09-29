package com.example.CoffeeApp.controller;


import com.example.CoffeeApp.Entity.CoffeeCategory;
import com.example.CoffeeApp.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<CoffeeCategory> listCategories = categoriesService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(listCategories);
    }
}
