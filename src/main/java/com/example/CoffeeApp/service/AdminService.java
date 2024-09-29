package com.example.CoffeeApp.service;
import com.example.CoffeeApp.DTO.AdminDTO.AdminCreationDTO;
import com.example.CoffeeApp.DTO.AdminDTO.AdminResponseDTO;
import com.example.CoffeeApp.Entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    AdminResponseDTO createAdmin(AdminCreationDTO adminCreationDTO);
    Optional<AdminCreationDTO> findByUsername(String username);
    boolean login(String username, String password);
    public List<Admin> getAdmin();
    public AdminResponseDTO getAdminById(Long userName);
    public AdminResponseDTO getMyInfor();
}

