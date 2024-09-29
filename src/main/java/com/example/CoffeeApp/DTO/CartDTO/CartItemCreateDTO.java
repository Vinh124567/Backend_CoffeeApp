package com.example.CoffeeApp.DTO.CartDTO;

import lombok.Data;

@Data
public class CartItemCreateDTO {
    private String userId;
    private Long coffeeId;
    private String size;
}

