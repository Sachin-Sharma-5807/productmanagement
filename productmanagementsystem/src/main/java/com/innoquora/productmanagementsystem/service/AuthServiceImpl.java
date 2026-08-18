package com.innoquora.productmanagementsystem.service;

import com.innoquora.productmanagementsystem.dto.request.LoginRequestDto;
import com.innoquora.productmanagementsystem.dto.request.RegisterRequestDto;
import com.innoquora.productmanagementsystem.dto.response.AuthResponseDto;
import com.innoquora.productmanagementsystem.dto.response.UserResponseDto;
import com.innoquora.productmanagementsystem.entity.User;
import com.innoquora.productmanagementsystem.exception.UserAlreadyExistsException;
import com.innoquora.productmanagementsystem.mapper.UserMapper;
import com.innoquora.productmanagementsystem.repository.UserRepository;
import com.innoquora.productmanagementsystem.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public UserResponseDto register(RegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new UserAlreadyExistsException("Email already registered: " + dto.getEmail());

        User saved = userRepository.save(UserMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword())));
        return UserMapper.toResponseDto(saved);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());
        String token = jwtUtil.generateToken(dto.getEmail());
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        return new AuthResponseDto(token, dto.getEmail(), role);
    }
}
