package com.innoquora.productmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
