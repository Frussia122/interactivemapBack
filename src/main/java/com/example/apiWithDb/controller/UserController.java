package com.example.apiWithDb.controller;

import com.example.apiWithDb.dto.UserDto;
import com.example.apiWithDb.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication authentication) {
        return userService.currentUser(authentication);
    }
}
