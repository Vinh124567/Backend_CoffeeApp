package com.example.CoffeeApp.service.implement;

import com.example.CoffeeApp.Entity.User;
import com.example.CoffeeApp.Entity.UserAddress;
import com.example.CoffeeApp.DTO.UserAddressDTO.UserAddressDTO;
import com.example.CoffeeApp.repository.UserAddressRepository;
import com.example.CoffeeApp.repository.UserRepository;
import com.example.CoffeeApp.service.UserAddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserAddressDTO> getUserAddresses(String userId) {
        List<UserAddress> userAddresses = userAddressRepository.findByUser_Id(userId);
        return userAddresses.stream()
                .map(address -> {
                    UserAddressDTO dto = modelMapper.map(address, UserAddressDTO.class);
                    dto.setUserId(address.getUser().getId()); // Gán id người dùng vào DTO
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void newAddresses(UserAddressDTO userAddressDTO){
        User user=userRepository.findById(userAddressDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserAddress userAddress = modelMapper.map(userAddressDTO, UserAddress.class);
        userAddress.setUser(user);
        userAddressRepository.save(userAddress);

    }


}
