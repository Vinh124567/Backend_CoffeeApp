package com.example.CoffeeApp.service;

import com.example.CoffeeApp.DTO.OrderDTO.OrderCreateDTO;

public interface OrderService {
    public OrderCreateDTO createOrder(OrderCreateDTO orderCreateDTO);
}
