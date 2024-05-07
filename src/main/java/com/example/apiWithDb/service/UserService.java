package com.example.apiWithDb.service;

import com.example.apiWithDb.dto.AuthDto;
import com.example.apiWithDb.dto.UserDto;
import com.example.apiWithDb.entities.User;
import com.example.apiWithDb.exception.AppException;
import com.example.apiWithDb.mappers.UserMapper;
import com.example.apiWithDb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto findByEmail(String email)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Email не найден", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login(AuthDto authDto){
        User user = userRepository.findByEmail(authDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(authDto.getPassword()),user.getPassword()))
        {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(AuthDto authDto)
    {
        Optional<User> optionalUser = userRepository.findByEmail(authDto.getEmail());
        if(optionalUser.isPresent())
        {
            throw new AppException("Login already exist", HttpStatus.CONFLICT);
        }
        User user = userMapper.signUpToUser(authDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(authDto.getPassword())));
        User saveduser = userRepository.save(user);
        return userMapper.toUserDto(user);
    }


}
