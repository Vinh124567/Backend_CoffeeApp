package com.example.CoffeeApp.service;

import com.example.CoffeeApp.DTO.CoffeeDTO.CoffeeResponseDTO;

import java.util.List;

public interface CoffeeService {
    public  List<CoffeeResponseDTO> findAllCoffee();
}
