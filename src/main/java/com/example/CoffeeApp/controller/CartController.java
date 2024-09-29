package com.example.CoffeeApp.controller;
import com.example.CoffeeApp.DTO.CartDTO.CartResponseDTO;
import com.example.CoffeeApp.DTO.CartDTO.CartItemCreateDTO;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService) {
            this.cartService = cartService;
        }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable String userId) {
        CartResponseDTO cartResponse = cartService.findByUserId(userId);
        ApiResponse<CartResponseDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(cartResponse);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long cartItemId){
            cartService.removeCartItem(cartItemId);
            ApiResponse apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Item deleted success: ");
            apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<?> addItemToCart(@RequestBody CartItemCreateDTO cartItem) {
            cartService.addItemToCart(cartItem);
            ApiResponse apiResponse = new ApiResponse<>();
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}


