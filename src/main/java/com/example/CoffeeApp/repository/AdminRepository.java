package com.example.CoffeeApp.repository;

import com.example.CoffeeApp.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);
    @Query("SELECT a FROM Admin a JOIN FETCH a.roles WHERE a.id = :id")
    Admin findAdminWithRoles(@Param("id") Long id);


    Boolean existsByUsername(String username);
}
