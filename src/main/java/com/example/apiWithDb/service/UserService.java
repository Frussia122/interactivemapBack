package com.example.apiWithDb.service;

import com.example.apiWithDb.dto.CredentialsDto;
import com.example.apiWithDb.dto.SignUpDto;
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

    public UserDto findBylogin(String login)
    {
        User user = userRepository.findBylogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto){
        User user = userRepository.findBylogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()),user.getPassword()))
        {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto)
    {
        Optional<User> optionalUser = userRepository.findBylogin(userDto.getLogin());

        if(optionalUser.isPresent())
        {
            throw new AppException("Login alrdy existst", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User saveduser = userRepository.save(user);

        return userMapper.toUserDto(user);
    }


}
