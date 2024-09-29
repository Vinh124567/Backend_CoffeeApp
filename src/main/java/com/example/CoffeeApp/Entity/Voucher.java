
package com.example.CoffeeApp.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @OneToMany(mappedBy = "voucher")
    private List<UserVoucher> userVouchers;

    @OneToMany(mappedBy = "voucher")
    private List<OrderVoucher> orderVouchers;

}

