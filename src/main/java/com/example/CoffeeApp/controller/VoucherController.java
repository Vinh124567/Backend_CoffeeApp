package com.example.CoffeeApp.controller;
import com.example.CoffeeApp.DTO.VoucherDTO.VoucherDTO;
import com.example.CoffeeApp.DTO.VoucherDTO.VoucherActionRequest;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.service.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/voucher")
public class VoucherController {
    private VoucherService voucherService;
    @GetMapping
    public ResponseEntity<?> getAllVouchers() {
        List<VoucherDTO> vouchers = voucherService.findAllVoucher();
        ApiResponse<List<VoucherDTO>>apiResponse = new ApiResponse<>();
        apiResponse.setData(vouchers);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createVoucher(@RequestBody VoucherDTO voucherDTO) {
        voucherService.createVoucher(voucherDTO);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Create success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/distribute/users/vouchers")
    public ResponseEntity<?> distributeToSpecificUser(@RequestBody VoucherActionRequest request) {
        List<Long> voucherIds = request.getVoucherIds();
        List<String> userIds = request.getUserIds();
        voucherService.distributeVouchersToUsers(voucherIds, userIds);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Distribute success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/distribute/all/voucher")
    public ResponseEntity<?> distributeVouchersToAll(@RequestBody VoucherActionRequest request) {
        voucherService.distributeVouchersToAllUsers(request.getVoucherIds());
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Distribute success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/users/{userId}/available")
    public ResponseEntity<?> getAvailableVouchersForUser(@PathVariable String userId) {
        List<VoucherDTO> vouchers = voucherService.getAvailableVouchersForUser(userId);
        ApiResponse<List<VoucherDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setData(vouchers);
        apiResponse.setMessage("Get success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PostMapping("/mark-as-used")
    public ResponseEntity<?> markVoucherAsUsed(@RequestBody VoucherActionRequest voucherActionRequest) {
        voucherService.markVouchersAsUsed(voucherActionRequest);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
