package com.example.CoffeeApp.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_addresses")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "name_customer", nullable = false)
    private String nameCustomer;

    @Column(name = "phone", nullable = false)
    private String phone ;


}

