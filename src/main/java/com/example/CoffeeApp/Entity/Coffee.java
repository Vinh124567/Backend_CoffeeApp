package com.example.CoffeeApp.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coffees")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CoffeeCategory category;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "description")
    private String description;
    @Column(name = "rating_average")
    private Double ratingAverage;
    @Column(name = "img_url")
    private String ImageUrl;
}
