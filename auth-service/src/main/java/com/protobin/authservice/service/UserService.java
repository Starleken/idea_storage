package com.protobin.authservice.service;

import com.protobin.authservice.dto.*;
import com.protobin.authservice.dto.token.RefreshTokenRequestDto;
import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface UserService {

    public ResponseEntity<UserResponseDto> findById(UUID id);

    public ResponseEntity<Void> signup(UserSignupDto signupDto);

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginDto);

    public ResponseEntity<RefreshTokenResponseDto> refresh(RefreshTokenRequestDto refreshDto);

    public ResponseEntity<Void> changePassword(ChangePasswordDto passwordDto);

    public ResponseEntity<Void> changeEmail(ChangeEmailDto emailDto);

    public ResponseEntity<Void> logout(String id);

    public ResponseEntity<Void> deleteById(UUID id);
}
