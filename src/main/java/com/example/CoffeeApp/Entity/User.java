package com.example.CoffeeApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Cart carts;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserVoucher> userVouchers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserAddress> addresses;

    public User() {
        this.addresses = new ArrayList<>();
    }

    // Getters and setters

    public Cart getCarts() {
        return carts;
    }

    public void setCart(Cart carts) {
        this.carts = carts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<UserVoucher> getUserVouchers() {
        return userVouchers;
    }

    public void setUserVouchers(List<UserVoucher> userVouchers) {
        this.userVouchers = userVouchers;
    }

    public List<UserAddress> getAddresses() {
        return addresses;
    }

    public void addAddress(UserAddress address) {
        addresses.add(address);
        address.setUser(this); // Set the bidirectional relationship
    }

    // Method to remove an address
    public void removeAddress(UserAddress address) {
        addresses.remove(address);
        address.setUser(null); // Remove the bidirectional relationship
    }

    public void setAddresses(List<UserAddress> addresses) {
        this.addresses = addresses;
    }
}
