package com.example.apiWithDb.controller;

import com.example.apiWithDb.config.UserAuthProvider;
import com.example.apiWithDb.dto.AuthDto;
import com.example.apiWithDb.dto.UserDto;
import com.example.apiWithDb.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthDto authDto){
       UserDto user  = userService.login(authDto);

        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/auth/registration")
    public ResponseEntity<UserDto> register(@RequestBody AuthDto authDto)
    {
        UserDto user = userService.register(authDto);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
                .body(user);
    }
    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication authentication) {
        return userService.currentUser(authentication);
    }
}
