package com.example.CoffeeApp.service;

import com.example.CoffeeApp.DTO.UserAddressDTO.UserAddressDTO;

import java.util.List;

public interface UserAddressService {
    public List<UserAddressDTO> getUserAddresses(String userId);
    public void  newAddresses(UserAddressDTO userAddressDTO);


}
