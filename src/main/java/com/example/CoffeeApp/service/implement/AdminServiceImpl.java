package com.example.CoffeeApp.service.implement;
import com.example.CoffeeApp.DTO.AdminDTO.AdminCreationDTO;
import com.example.CoffeeApp.DTO.AdminDTO.AdminResponseDTO;
import com.example.CoffeeApp.DTO.RoleDTO.RoleDTO;
import com.example.CoffeeApp.Entity.Role;
import com.example.CoffeeApp.Exception.AppException;
import com.example.CoffeeApp.Exception.ErrorCode;
import com.example.CoffeeApp.Entity.Admin;
import com.example.CoffeeApp.repository.AdminRepository;
import com.example.CoffeeApp.repository.RoleRepository;
import com.example.CoffeeApp.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public AdminResponseDTO createAdmin(AdminCreationDTO adminCreationDTO) {
        if (adminRepository.existsByUsername(adminCreationDTO.getUsername())) {
            throw new AppException(ErrorCode.NAME_EXISTED);
        }

        Admin admin = modelMapper.map(adminCreationDTO, Admin.class);
        admin.setPassword(passwordEncoder.encode(adminCreationDTO.getPassword()));

        List<Role> roles = new ArrayList<>();
        for (Long roleId : adminCreationDTO.getRoleIds()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            roles.add(role);
        }
        admin.setRoles(roles);

        adminRepository.save(admin);

        AdminResponseDTO response = modelMapper.map(admin, AdminResponseDTO.class);
        response.setRoles(roles.stream()
                .map(role -> new RoleDTO(role.getId(), role.getName()))
                .collect(Collectors.toSet())); // Chuyển đổi Role sang RoleDTO

        Optional<Admin> a =adminRepository.findByUsername("admin");
        System.out.println("Authenticated Admin: " + a);
        Optional<Admin> b =adminRepository.findByUsername("admin");
        System.out.println("Authenticated Admin: " + b);
        return response;
    }




    @Override
    @Transactional
    public Optional<AdminCreationDTO> findByUsername(String username) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin.map(value -> {
            AdminCreationDTO dto = modelMapper.map(value, AdminCreationDTO.class);
            dto.setRoleIds(value.getRoles().stream().map(Role::getId).collect(Collectors.toSet())); // Thêm roles nếu cần
            return dto;
        });
    }


    @Override
    public boolean login(String username, String password) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return admin.getPassword().equals(password);
        }
        return false;
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Admin> getAdmin() {
        var authentication= SecurityContextHolder.getContext().getAuthentication();
        log.info("Username:{}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()) );
        return adminRepository.findAll();
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public AdminResponseDTO getAdminById(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        Admin admin = optionalAdmin.orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + id));
        return modelMapper.map(admin, AdminResponseDTO.class);
    }

    public AdminResponseDTO getMyInfor(){
        var context=SecurityContextHolder.getContext();
       String name= context.getAuthentication().getName();
       Admin admin= adminRepository.findByUsername(name).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND));
        return modelMapper.map(admin, AdminResponseDTO.class);
    }


}

