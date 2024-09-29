//package com.example.CoffeeApp.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "user_vouchers")
//public class UserVoucher {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "voucher_id", nullable = false)
//    @JsonBackReference
//    private Voucher voucher;
//
//    @Column(name = "used", nullable = false)
//    private Boolean used = false;
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Voucher getVoucher() {
//        return voucher;
//    }
//
//    public void setVoucher(Voucher voucher) {
//        this.voucher = voucher;
//    }
//
//    public Boolean getUsed() {
//        return used;
//    }
//
//    public void setUsed(Boolean used) {
//        this.used = used;
//    }
//}
//
package com.example.CoffeeApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_vouchers")
public class UserVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;
    @Column(name = "used", nullable = false)
    private Boolean used = false;

    public UserVoucher(User user, Voucher voucher, Boolean used) {
        this.user = user;
        this.voucher = voucher;
        this.used = used;
    }
}
