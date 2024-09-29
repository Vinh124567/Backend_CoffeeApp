package com.example.CoffeeApp.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cartid", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "coffeeid", referencedColumnName = "id", nullable = false)
    private Coffee coffee;

    @Column(name = "size")
    private String size;
}



