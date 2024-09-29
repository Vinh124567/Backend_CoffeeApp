package com.example.CoffeeApp.service.implement;
import com.example.CoffeeApp.DTO.CartDTO.CartItemResponseDTO;
import com.example.CoffeeApp.DTO.CartDTO.CartResponseDTO;
import com.example.CoffeeApp.DTO.CartDTO.CartItemCreateDTO;
import com.example.CoffeeApp.DTO.CoffeeDTO.CoffeeResponseDTO;
import com.example.CoffeeApp.Entity.Cart;
import com.example.CoffeeApp.Entity.CartItem;
import com.example.CoffeeApp.Entity.Coffee;
import com.example.CoffeeApp.repository.CartItemRepository;
import com.example.CoffeeApp.repository.CartRepository;
import com.example.CoffeeApp.repository.CoffeeRepository;
import com.example.CoffeeApp.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private CoffeeRepository coffeeRepository;
    private CartItemRepository cartItemRepository;

    @Override
    public String createCart(Cart cart) {
        cartRepository.save(cart);
        return "create success";
    }

    public CartResponseDTO findByUserId(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
        //dƯỚi ĐÂy lÀ cOmMent
        // Tạo đối tượng CartResponseDTO từ cart
        CartResponseDTO responseDTO = modelMapper.map(cart, CartResponseDTO.class);
        responseDTO.setUserId(cart.getUser().getId());

        // Ánh xạ cartItems sang CartItemResponseDTO
        List<CartItemResponseDTO> cartItemResponseDTOS = cart.getCartItems().stream()
                .map(cartItem -> {
                    CoffeeResponseDTO coffeeResponseDTO = modelMapper.map(cartItem.getCoffee(), CoffeeResponseDTO.class);
                    coffeeResponseDTO.setCategory(cartItem.getCoffee().getCategory().getName());
                    return new CartItemResponseDTO(cartItem.getId(), cartItem.getSize(), coffeeResponseDTO);
                })
                .collect(Collectors.toList());
        responseDTO.setCartItems(cartItemResponseDTOS);

        return responseDTO;
    }


    @Override
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem addItemToCart(CartItemCreateDTO cartItemCreateDTO) {
        Cart cart = cartRepository.findByUserId(cartItemCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + cartItemCreateDTO.getUserId()));
        Coffee coffee = coffeeRepository.findById(cartItemCreateDTO.getCoffeeId())
                .orElseThrow(() -> new RuntimeException("Coffee not found for ID: " + cartItemCreateDTO.getCoffeeId()));
        CartItem cartItem = modelMapper.map(cartItemCreateDTO, CartItem.class);
        cartItem.setCart(cart);
        cartItem.setCoffee(coffee);
        return cartItemRepository.save(cartItem);
    }
}
