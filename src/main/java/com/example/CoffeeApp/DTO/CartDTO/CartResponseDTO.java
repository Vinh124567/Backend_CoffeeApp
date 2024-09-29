package com.example.CoffeeApp.DTO.CartDTO;
import lombok.Data;

import java.util.List;
@Data
public class CartResponseDTO {
    private Long id;
    private String userId;
    private List<CartItemResponseDTO> cartItems;
}
