package com.example.CoffeeApp.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private final CartService cartService;
//
//    public UserController(CartService cartService) {
//        this.cartService = cartService;
//    }
//
//    @GetMapping("/{userId}")
//    public Object getUser(@PathVariable String userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            return new ResponseSuccess(HttpStatus.OK, "User retrieved successfully", user.get());
//        } else {
//            return new ResponseFailure(HttpStatus.NOT_FOUND, "No user found for the given ID");
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseSuccess createUser(@RequestBody User user) {
//        Cart cart = new Cart();
//        cart.setUser(user);
//        user.setCart(cart);
//        userRepository.save(user);
//        return new ResponseSuccess(HttpStatus.CREATED, "User created successfully.");
//    }

}
