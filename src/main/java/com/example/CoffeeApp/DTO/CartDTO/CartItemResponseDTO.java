package com.example.CoffeeApp.DTO.CartDTO;
import com.example.CoffeeApp.DTO.CoffeeDTO.CoffeeResponseDTO;
import com.example.CoffeeApp.Entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemResponseDTO {
    private Long id;
    private String size;
    private CoffeeResponseDTO coffeeResponseDTO;
}

