package com.example.CoffeeApp.controller;
import com.example.CoffeeApp.DTO.AdminDTO.AdminCreationDTO;
import com.example.CoffeeApp.DTO.AdminDTO.AdminResponseDTO;
import com.example.CoffeeApp.Entity.Admin;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminCreationDTO adminCreationDTO) {
        AdminResponseDTO createdAdmin = adminService.createAdmin(adminCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = adminService.login(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> GetByUserName(@PathVariable String username) {
        Optional<AdminCreationDTO> adminCreationDTO  = adminService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(adminCreationDTO);
    }

    @GetMapping("/status")
    public ResponseEntity<?> GetUserStatus() {
        List<Admin> admin = adminService.getAdmin();
        ApiResponse apiResponse=new ApiResponse<>();
        apiResponse.setData(admin);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<?> GetUserById(@PathVariable Long id) {
        AdminResponseDTO adminResponseDTO=adminService.getAdminById(id);
        return ResponseEntity.status(HttpStatus.OK).body(adminResponseDTO);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<?> GetMyInfo() {
        AdminResponseDTO adminResponseDTO=adminService.getMyInfor();
        return ResponseEntity.status(HttpStatus.OK).body(adminResponseDTO);
    }


}

