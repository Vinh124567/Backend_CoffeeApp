package com.example.CoffeeApp.DTO.VoucherDTO;
import com.example.CoffeeApp.Entity.Voucher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class VoucherDTO {
    private Long id;
    private String code;
    private String description;
    private Double discount;
    private LocalDate validFrom;
    private LocalDate validUntil;
    public VoucherDTO(Voucher voucher) {
        this.id = voucher.getId();
        this.code = voucher.getCode();
        this.description = voucher.getDescription();
        this.discount = voucher.getDiscount();
        this.validFrom = voucher.getValidFrom();
        this.validUntil = voucher.getValidUntil();
    }

}


