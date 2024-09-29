package com.example.CoffeeApp.DTO.AdminDTO;

import com.example.CoffeeApp.DTO.RoleDTO.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {
    private Long id;
    private String username;
    private Set<RoleDTO> roles;
}
