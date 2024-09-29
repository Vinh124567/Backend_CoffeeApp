package com.example.CoffeeApp.repository;
import com.example.CoffeeApp.Entity.UserVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserVoucherRepository extends JpaRepository<UserVoucher, Long> {
    Optional<UserVoucher> findByUserIdAndVoucherId(String userId, Long voucherId);
    List<UserVoucher> findByUserIdAndUsed(String userId, Boolean used);

}
