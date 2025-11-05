package com.protobin.authservice.service.impl;

import com.protobin.authservice.dto.*;
import com.protobin.authservice.dto.token.RefreshTokenRequestDto;
import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import com.protobin.authservice.entity.UserEntity;
import com.protobin.authservice.mapper.UserMapper;
import com.protobin.authservice.repository.UserRepository;
import com.protobin.authservice.service.EncodingService;
import com.protobin.authservice.service.TokenService;
import com.protobin.authservice.service.UserService;
import com.protobin.authservice.utils.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.protobin.authservice.utils.ExceptionUtils.*;
import static com.protobin.authservice.utils.HttpUtils.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final EncodingService encodingService;
    private final TokenService tokenService;

    @Override
    public UserResponseDto findById(UUID id) {
        var found = userRepository.findById(id)
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        return userMapper.mapToDto(found);
    }

    @Override
    public void signup(UserSignupDto signupDto) {
        checkIfUniqueFieldsExists(signupDto.getEmail(), signupDto.getUsername());

        var toSave = userMapper.mapToEntity(signupDto);
        toSave.setPassword(encodingService.encode(signupDto.getPassword()));

        userRepository.save(toSave);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginDto, HttpServletRequest request) {
        var found = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        if (!encodingService.isMatch(loginDto.getPassword(), found.getPassword())) {
            throwIllegalActionException("User by email: " + loginDto.getEmail() + ". Invalid password");
        }

        var requestInfo = parseRequestInfo(request);

        return LoginResponseDto.builder()
                .userId(found.getId())
                .accessToken(tokenService.generateAccessToken(found.getId()))
                .refreshToken(tokenService.generateRefreshToken(found.getId(), requestInfo))
                .build();
    }

    @Override
    public RefreshTokenResponseDto refresh(RefreshTokenRequestDto refreshDto, HttpServletRequest request) {
        var refreshToken = refreshDto.getRefreshToken();

        var requestInfo = parseRequestInfo(request);

        return tokenService.refresh(refreshToken, requestInfo);
    }

    @Override
    public void changePassword(ChangePasswordDto passwordDto) {
        var found = userRepository.findById(passwordDto.getId())
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        if (encodingService.isMatch(passwordDto.getOldPassword(), found.getPassword())) {
            found.setPassword(encodingService.encode(passwordDto.getNewPassword()));
            userRepository.save(found);
            return;
        }

        throwIllegalActionException("Passwords don't match");
    }

    @Override
    public void changeEmail(ChangeEmailDto emailDto) {
        checkIfEmailExists(emailDto.getNewEmail());

        var found = userRepository.findById(emailDto.getId())
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));
        found.setEmail(emailDto.getNewEmail());

        userRepository.save(found);
    }

    @Override
    public void logout(LogoutRequestDto logoutDto) {
        tokenService.deleteByToken(logoutDto.getRefreshToken());
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> getEntityNotFoundException(UserEntity.class));

        userRepository.deleteById(id);
    }

    private void checkIfUniqueFieldsExists(String email, String username) {
        checkIfEmailExists(email);
        checkIfUsernameExists(username);
    }

    private void checkIfEmailExists(String email) {
        var found = userRepository.findByEmail(email);

        if (found.isPresent()) {
            throwIllegalActionException("Email: " + email + " exists");
        }
    }

    private void checkIfUsernameExists(String username) {
        var found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throwIllegalActionException("Username: " + username + " exists");
        }
    }
}
