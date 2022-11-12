package com.bookstore.controller;

import com.bookstore.payload.request.LoginRequest;
import com.bookstore.payload.response.JwtResponse;
import com.bookstore.security.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private JwtServiceImpl jwtService;
    @PostMapping("/auth/login")
    public JwtResponse createJwtToken(@RequestBody LoginRequest loginRequest) throws Exception {
        return jwtService.createJwtToken(loginRequest);
    }
}
