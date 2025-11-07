package com.protobin.authservice.service;

import com.protobin.authservice.dto.*;
import com.protobin.authservice.dto.token.RefreshTokenRequestDto;
import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface UserService {

    public UserResponseDto findById(UUID id);

    public void signup(UserSignupDto signupDto);

    public LoginResponseDto login(LoginRequestDto loginDto, HttpServletRequest request,
                                  HttpServletResponse response);

    public RefreshTokenResponseDto refresh(RefreshTokenRequestDto refreshDto, HttpServletRequest request,
                                           HttpServletResponse response);

    public void changePassword(ChangePasswordDto passwordDto);

    public void changeEmail(ChangeEmailDto emailDto);

    public void logout(LogoutRequestDto logoutDto);

    public void deleteById(UUID id);
}
