package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.AuthResponse;
import com.uni.hotelproject.dto.LoginRequest;
import com.uni.hotelproject.dto.RegisterRequest;
import com.uni.hotelproject.entity.User;
import com.uni.hotelproject.exception.InvalidPasswordExcepction;
import com.uni.hotelproject.exception.UserIDAlreadyExistsException;
import com.uni.hotelproject.exception.UserNotFoundExcepction;
import com.uni.hotelproject.exception.UsernameAlreadyExistsException;
import com.uni.hotelproject.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundExcepction("User not found"));
        String token = jwtService.getToken(user);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordExcepction("Invalid password");
        }

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Transactional
    public AuthResponse register(RegisterRequest request){

        if (userRepository.findByUsername(request.getUserID()).isPresent()) {
            throw new UserIDAlreadyExistsException("User with Id" + request.getUserID() + " already exists");
        }
        if (userRepository.findByUserId(request.getUsername()).isPresent()) {
            throw new UserIDAlreadyExistsException("This username" + request.getUsername() + "already exists");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UsernameAlreadyExistsException("This email" + request.getEmail() + "already exists");
        }
        User user = User.builder()
                .userID(request.getUserID())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .completeName(request.getCompleteName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
