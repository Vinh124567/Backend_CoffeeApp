package com.example.CoffeeApp.DTO.VoucherDTO;

import lombok.Data;

import java.util.List;
@Data
public class VoucherActionRequest {
    private List<Long> voucherIds;
    private List<String> userIds;
    private String userId;
}
