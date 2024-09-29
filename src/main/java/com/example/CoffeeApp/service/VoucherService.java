package com.example.CoffeeApp.service;

import com.example.CoffeeApp.DTO.VoucherDTO.VoucherActionRequest;
import com.example.CoffeeApp.DTO.VoucherDTO.VoucherDTO;

import java.util.List;

public interface VoucherService {
    public List<VoucherDTO> findAllVoucher();
    public void distributeVouchersToUsers(List<Long> voucherIds, List<String> userIds);
    public void markVouchersAsUsed(VoucherActionRequest voucherActionRequest);
    public List<VoucherDTO> getAvailableVouchersForUser(String userId);
    public void createVoucher(VoucherDTO voucherDTO);
    public void distributeVouchersToAllUsers(List<Long> voucherIds);

}
