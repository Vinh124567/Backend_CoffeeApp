package com.example.CoffeeApp.DTO.AdminDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class AdminCreationDTO {
    private String username;
    private String password;
    private Set<Long> roleIds;
}
