package com.uni.hotelproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @PostMapping("/demo")
    public String welcome() {
        return "Welcome to the Hotel Project API!";
    }
}
