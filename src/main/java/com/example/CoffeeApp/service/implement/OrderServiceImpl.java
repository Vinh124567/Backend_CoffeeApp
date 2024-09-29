package com.example.CoffeeApp.service.implement;

import com.example.CoffeeApp.DTO.OrderDTO.OrderCreateDTO;
import com.example.CoffeeApp.DTO.OrderDTO.OrderItemDTO;
import com.example.CoffeeApp.Entity.*;
import com.example.CoffeeApp.repository.*;
import com.example.CoffeeApp.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final VoucherRepository voucherRepository;
    private final UserVoucherRepository userVoucherRepository;
    private final CoffeeRepository coffeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderCreateDTO createOrder(OrderCreateDTO orderCreateDTO) {
        User user = userRepository.findById(orderCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserAddress userAddress = userAddressRepository.findById(orderCreateDTO.getUserAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = modelMapper.map(orderCreateDTO, Order.class);
        order.setUser(user);
        order.setAddress(userAddress);
        order.setOrderItems(new ArrayList<>());
        order.setOrderVouchers(new ArrayList<>());
        createOrderItems(orderCreateDTO.getOrderItems(), order);
        processVouchers(orderCreateDTO.getVoucherCodes(), order, user);
        orderRepository.save(order);
        return orderCreateDTO;
    }

    private void createOrderItems(List<OrderItemDTO> orderItemsDTO, Order order) {
        for (OrderItemDTO itemDTO : orderItemsDTO) {
            OrderItem orderItem = modelMapper.map(orderItemsDTO, OrderItem.class);
            Coffee coffee = coffeeRepository.findById(itemDTO.getCoffeeId())
                    .orElseThrow(() -> new RuntimeException("Coffee not found"));
            orderItem.setCoffee(coffee);
            orderItem.setOrder(order);
            orderItem.setQuantity(itemDTO.getQuantity());
            order.getOrderItems().add(orderItem);
        }
    }

    private void processVouchers(List<String> voucherCodes, Order order, User user) {
        if (voucherCodes == null || voucherCodes.isEmpty()) {
            return;
        }
        for (String voucherCode : voucherCodes) {
            Voucher voucher = voucherRepository.findByCode(voucherCode)
                    .orElseThrow(() -> new RuntimeException("Voucher not found"));

            UserVoucher userVoucher = userVoucherRepository.findByUserIdAndVoucherId(user.getId(), voucher.getId())
                    .orElseThrow(() -> new RuntimeException("User voucher not found"));

            if (userVoucher.getUsed()) {
                throw new RuntimeException("Voucher " + voucherCode + " has already been used");
            }

            OrderVoucher orderVoucher = new OrderVoucher();
            orderVoucher.setOrder(order);
            orderVoucher.setVoucher(voucher);
            order.getOrderVouchers().add(orderVoucher);
            userVoucher.setUsed(true);
            userVoucherRepository.save(userVoucher);
        }
    }
}
