package com.example.CoffeeApp.service.implement;

import com.example.CoffeeApp.DTO.CoffeeDTO.CoffeeResponseDTO;
import com.example.CoffeeApp.Exception.AppException;
import com.example.CoffeeApp.Exception.ErrorCode;
import com.example.CoffeeApp.Entity.Coffee;
import com.example.CoffeeApp.repository.CoffeeRepository;
import com.example.CoffeeApp.service.CoffeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {
    CoffeeRepository coffeeRepository;
    private ModelMapper modelMapper;

    @Override
    public List<CoffeeResponseDTO> findAllCoffee() {
        List<Coffee> coffees = coffeeRepository.findAll();
        if (coffees == null || coffees.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        TypeMap<Coffee, CoffeeResponseDTO> typeMap = modelMapper.createTypeMap(Coffee.class, CoffeeResponseDTO.class);
        typeMap.addMappings(mapper -> {
            mapper.map(src -> src.getCategory().getName(), CoffeeResponseDTO::setCategory);
        });
        return coffees.stream()
                .map(coffee -> typeMap.map(coffee))
                .collect(Collectors.toList());
    }

}
