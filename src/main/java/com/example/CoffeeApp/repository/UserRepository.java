package com.example.CoffeeApp.repository;
import com.example.CoffeeApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT u.id FROM User u")
    List<String> findAllUserIds();
}
