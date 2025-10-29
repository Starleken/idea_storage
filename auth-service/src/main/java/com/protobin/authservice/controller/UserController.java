package com.protobin.authservice.controller;

import com.protobin.authservice.dto.*;
import com.protobin.authservice.dto.token.RefreshTokenRequestDto;
import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody  UserSignupDto signupDto) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto) {
        return null;
    }

    @PostMapping("/tokens/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshDto) {
        return null;
    }

    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDto passwordDto) {
        return null;
    }

    @PutMapping("/email")
    public ResponseEntity<Void> changeEmail(@RequestBody ChangeEmailDto emailDto) {
        return null;
    }

    @PutMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        return null;
    }
}
