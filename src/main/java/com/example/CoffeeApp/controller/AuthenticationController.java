package com.example.CoffeeApp.controller;
import com.example.CoffeeApp.DTO.AuthenticationDTO.AuthenticationRequestDTO;
import com.example.CoffeeApp.DTO.AuthenticationDTO.IntrospectRequest;
import com.example.CoffeeApp.response.ApiResponse;
import com.example.CoffeeApp.response.AuthenticationResponse;
import com.example.CoffeeApp.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    AuthenticationService authenticationService ;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO){
           var result= authenticationService.Authenticate(authenticationRequestDTO);
           ApiResponse apiResponse= new ApiResponse();
           apiResponse.setData(result);
           return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/introspect")
    ResponseEntity<?> authenticate(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var result= authenticationService.introspect(introspectRequest);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setData(result);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
