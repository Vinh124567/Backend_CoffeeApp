package com.example.CoffeeApp.service;


import com.example.CoffeeApp.DTO.AuthenticationDTO.AuthenticationRequestDTO;
import com.example.CoffeeApp.DTO.AuthenticationDTO.IntrospectRequest;
import com.example.CoffeeApp.DTO.AuthenticationDTO.IntrospectResponse;
import com.example.CoffeeApp.response.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    public AuthenticationResponse Authenticate(AuthenticationRequestDTO authenticationRequestDTO);

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
