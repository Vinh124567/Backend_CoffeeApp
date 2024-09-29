package com.example.CoffeeApp.service.implement;

import com.example.CoffeeApp.Entity.CoffeeCategory;
import com.example.CoffeeApp.repository.CategoriesRepository;
import com.example.CoffeeApp.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public List<CoffeeCategory> getAllCategories() {
        return categoriesRepository.findAll();
    }
}
