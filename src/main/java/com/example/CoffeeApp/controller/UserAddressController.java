package com.example.CoffeeApp.controller;

import com.example.CoffeeApp.DTO.UserAddressDTO.UserAddressDTO;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.service.UserAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/address")
public class UserAddressController {
    private final  UserAddressService userAddressService;

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserAddresses(@PathVariable String userId) {
        List<UserAddressDTO> addressesResponse = userAddressService.getUserAddresses(userId);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setData(addressesResponse);
        apiResponse.setMessage("Get addresses success");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<?> NewAddress(@RequestBody UserAddressDTO userAddressRequestDTO) {
      userAddressService.newAddresses(userAddressRequestDTO);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Create addresses success");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}

