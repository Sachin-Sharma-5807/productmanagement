package com.innoquora.productmanagementsystem.controller;

import com.innoquora.productmanagementsystem.dto.request.LoginRequestDto;
import com.innoquora.productmanagementsystem.dto.request.RegisterRequestDto;
import com.innoquora.productmanagementsystem.dto.response.AuthResponseDto;
import com.innoquora.productmanagementsystem.dto.response.UserResponseDto;
import com.innoquora.productmanagementsystem.payload.ApiResponse;
import com.innoquora.productmanagementsystem.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirements

public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(@Valid @RequestBody RegisterRequestDto dto){

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User resgister SuccessFully",authService.register(dto)));

    }

    @PostMapping("/login")

    public ResponseEntity<ApiResponse<AuthResponseDto>>login(@Valid @RequestBody LoginRequestDto dto){
        return ResponseEntity.ok()
                .body(ApiResponse.success("User login Successfully",authService.login(dto)));
    }
}




