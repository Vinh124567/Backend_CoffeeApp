//package com.example.CoffeeApp.Entity;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.Set;
//
//@Entity
//@Data
//@Table(name = "roles")
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", nullable = false, unique = true)
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<Admin> admins;
//}
//
//
//
//
//
package com.example.CoffeeApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Admin> admins; // Thay Set<Admin> bằng List<Admin>
}
