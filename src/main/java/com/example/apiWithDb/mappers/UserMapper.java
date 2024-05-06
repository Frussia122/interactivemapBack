package com.example.apiWithDb.mappers;

import com.example.apiWithDb.dto.SignUpDto;
import com.example.apiWithDb.dto.UserDto;
import com.example.apiWithDb.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target ="password", ignore = true)
    User signUpToUser(SignUpDto userDto);
}
