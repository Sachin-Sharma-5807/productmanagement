package com.innoquora.productmanagementsystem.mapper;

import com.innoquora.productmanagementsystem.dto.request.RegisterRequestDto;
import com.innoquora.productmanagementsystem.dto.response.UserResponseDto;
import com.innoquora.productmanagementsystem.entity.User;

public class UserMapper {

    public static User toEntity(RegisterRequestDto dto, String encodedPassword) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encodedPassword)
                .role("USER")
                .build();
    }

    public static UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
