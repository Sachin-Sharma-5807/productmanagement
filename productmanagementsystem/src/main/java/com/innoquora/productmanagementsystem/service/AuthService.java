package com.innoquora.productmanagementsystem.service;

import com.innoquora.productmanagementsystem.dto.request.LoginRequestDto;
import com.innoquora.productmanagementsystem.dto.request.RegisterRequestDto;
import com.innoquora.productmanagementsystem.dto.response.AuthResponseDto;
import com.innoquora.productmanagementsystem.dto.response.UserResponseDto;

public  interface  AuthService{
    UserResponseDto register(RegisterRequestDto registerRequestDto);
    AuthResponseDto login(LoginRequestDto loginRequestDto);
}