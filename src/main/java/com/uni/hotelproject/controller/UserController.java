package com.uni.hotelproject.controller;

import com.uni.hotelproject.config.JwtAuthenticationFilter;
import com.uni.hotelproject.dto.UserDTO;
import com.uni.hotelproject.entity.User;
import com.uni.hotelproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    private final UserService userService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public UserController(UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getTokenfromRequest(request);
        if (token == null || !jwtAuthenticationFilter.validateToken(token)) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String username = jwtAuthenticationFilter.getUsernameFromToken(token);

        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        UserDTO userDTO = UserDTO.builder().
                userID(currentUser.getUserID()).
                username(currentUser.getUsername()).
                completeName(currentUser.getCompleteName()).
                email(currentUser.getEmail()).
                role(currentUser.getRole()).
                build();
        return ResponseEntity.ok(userDTO);
    }

}
