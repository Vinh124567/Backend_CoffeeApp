package com.example.CoffeeApp.controller;
import com.example.CoffeeApp.DTO.OrderDTO.OrderCreateDTO;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO orderDTO) {
        OrderCreateDTO createdOrder = orderService.createOrder(orderDTO);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setData(createdOrder);
        apiResponse.setMessage("Create order success");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}

