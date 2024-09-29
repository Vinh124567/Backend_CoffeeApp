package com.example.CoffeeApp.DTO.OrderDTO;

import com.example.CoffeeApp.Entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {
    private Long id;
    @NotNull(message = "User ID cannot be null")
    private String userId;

    @NotNull(message = "Total Price cannot be null")
    private Double totalPrice;

    private String notes;

    @NotNull(message = "Order status cannot be null")
    private OrderStatus status;

    @NotNull(message = "Order items cannot be null")
    private List<OrderItemDTO> orderItems;

    private List<String> voucherCodes;

    @NotNull(message = "User address cannot be null")
    private long userAddressId;
}
