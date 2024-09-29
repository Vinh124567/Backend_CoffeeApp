package com.example.CoffeeApp.DTO.AuthenticationDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequestDTO {
    String username;
    String password;
}
