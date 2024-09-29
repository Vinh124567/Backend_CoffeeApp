package com.example.CoffeeApp.controller;
import com.example.CoffeeApp.DTO.CoffeeDTO.CoffeeResponseDTO;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/coffee")
public class CoffeeController {
    private final CoffeeService coffeeService;
    @GetMapping()
    public ResponseEntity<?> getAllCoffee() {
        List<CoffeeResponseDTO> listCoffee = coffeeService.findAllCoffee();
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(listCoffee);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}

