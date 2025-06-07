package com.uni.hotelproject.controller;

import com.uni.hotelproject.dto.AuthResponse;
import com.uni.hotelproject.dto.LoginRequest;
import com.uni.hotelproject.dto.RegisterRequest;
import com.uni.hotelproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping( "/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping( "/auth/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(authService.register(request));
    }
}
