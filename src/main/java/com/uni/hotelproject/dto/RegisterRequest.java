package com.uni.hotelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userID;
    private String username;
    private String completeName;
    private String email;
    private String password;
    private String role;
}
