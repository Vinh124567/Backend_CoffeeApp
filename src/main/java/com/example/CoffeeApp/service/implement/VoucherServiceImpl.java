package com.example.CoffeeApp.service.implement;
import com.example.CoffeeApp.DTO.VoucherDTO.VoucherActionRequest;
import com.example.CoffeeApp.Exception.AppException;
import com.example.CoffeeApp.Exception.ErrorCode;
import com.example.CoffeeApp.Entity.User;
import com.example.CoffeeApp.Entity.UserVoucher;
import com.example.CoffeeApp.Entity.Voucher;
import com.example.CoffeeApp.DTO.VoucherDTO.VoucherDTO;
import com.example.CoffeeApp.repository.UserRepository;
import com.example.CoffeeApp.repository.UserVoucherRepository;
import com.example.CoffeeApp.repository.VoucherRepository;
import com.example.CoffeeApp.service.VoucherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class VoucherServiceImpl implements VoucherService {
    private VoucherRepository voucherRepository;
    private UserRepository userRepository;
    private UserVoucherRepository userVoucherRepository;
    private ModelMapper modelMapper;
    @Override
    public List<VoucherDTO> findAllVoucher() {
        List<Voucher> vouchers = voucherRepository.findAll();
        if (vouchers == null) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return vouchers.stream()
                .map(voucher -> modelMapper.map(voucher, VoucherDTO.class))
                .collect(Collectors.toList());
    }

    private void distributeVouchers(List<User> users, List<Voucher> vouchers) {
        List<UserVoucher> userVouchersToSave = users.stream()
                .flatMap(user -> vouchers.stream()
                        .filter(voucher -> userVoucherRepository.findByUserIdAndVoucherId(user.getId(), voucher.getId()).isEmpty())
                        .map(voucher -> new UserVoucher(user, voucher, false)))
                .collect(Collectors.toList());

        userVoucherRepository.saveAll(userVouchersToSave);
    }

    @Override
    public void distributeVouchersToUsers(List<Long> voucherIds, List<String> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        List<Voucher> vouchers = voucherRepository.findAllById(voucherIds);
        distributeVouchers(users, vouchers);
    }



    @Override
    public void distributeVouchersToAllUsers(List<Long> voucherIds) {
        List<String> allUserIds = userRepository.findAllUserIds();
        List<User> users = userRepository.findAllById(allUserIds);
        List<Voucher> vouchers = voucherRepository.findAllById(voucherIds);
        distributeVouchers(users, vouchers);
    }

    @Override
    public void markVouchersAsUsed(VoucherActionRequest voucherActionRequest) {
        String userId = voucherActionRequest.getUserId(); // Lấy ID của người dùng
        List<Long> voucherIds = voucherActionRequest.getVoucherIds(); // Lấy danh sách voucher ID

        for (Long voucherId : voucherIds) { // Lặp qua từng voucher ID
            Optional<UserVoucher> userVoucher = userVoucherRepository.findByUserIdAndVoucherId(userId, voucherId);
            if (userVoucher.isPresent()) {
                UserVoucher uv = userVoucher.get();
                uv.setUsed(true); // Đánh dấu voucher là đã dùng
                userVoucherRepository.save(uv); // Lưu lại thay đổi
            } else {
                throw new RuntimeException("Voucher ID " + voucherId + " not found for user " + userId + ".");
            }
        }
    }



    @Override
    public List<VoucherDTO> getAvailableVouchersForUser(String userId) {
        List<UserVoucher> userVouchers = userVoucherRepository.findByUserIdAndUsed(userId, false);
        return userVouchers.stream()
                .map(UserVoucher::getVoucher)
                .map(VoucherDTO::new)
                .collect(Collectors.toList());
    }


    @Override
    public void createVoucher(VoucherDTO voucherDTO) {
        Optional<Voucher> existingVoucher = voucherRepository.findByCode(voucherDTO.getCode());
        if (existingVoucher.isPresent()) {
            throw new AppException(ErrorCode.NAME_EXISTED);
        }
        Voucher voucher = modelMapper.map(voucherDTO, Voucher.class);
        voucherRepository.save(voucher);
    }

}
