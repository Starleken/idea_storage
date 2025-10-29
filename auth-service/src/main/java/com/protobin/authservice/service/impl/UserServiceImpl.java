package com.protobin.authservice.service.impl;

import com.protobin.authservice.dto.*;
import com.protobin.authservice.dto.token.RefreshTokenRequestDto;
import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import com.protobin.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<UserResponseDto> findById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> signup(UserSignupDto signupDto) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginDto) {
        return null;
    }

    @Override
    public ResponseEntity<RefreshTokenResponseDto> refresh(RefreshTokenRequestDto refreshDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> changePassword(ChangePasswordDto passwordDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> changeEmail(ChangeEmailDto emailDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logout(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        return null;
    }
}
