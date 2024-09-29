package com.example.CoffeeApp.DTO.CoffeeDTO;

import lombok.Data;

@Data
public class CoffeeResponseDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private Double price;
    private String description;
    private Double ratingAverage;
    private String category;
}
