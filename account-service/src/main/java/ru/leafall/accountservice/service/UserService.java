package ru.leafall.accountservice.service;

import ru.leafall.accountservice.dto.token.TokenAccessDto;
import ru.leafall.accountservice.dto.token.TokenRefreshDto;
import ru.leafall.accountservice.dto.token.TokenResponseDto;
import ru.leafall.accountservice.dto.user.*;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;

import java.util.List;
import java.util.Set;

public interface UserService {
    PaginationResponse<UserResponseDto> findAll(PaginationParams params);
    List<UserResponseDto> findAllByIds(Set<Long> ids);
    UserResponseDto findById(Long id);
    TokenResponseDto signUp(UserCreateDto dto);
    UserResponseDto update(UserUpdateDto dto);
    UserResponseDto deleteById(Long id);
    TokenResponseDto signIn(SignInDto dto);
    TokenResponseDto refresh(TokenRefreshDto dto);
    void logout(TokenRefreshDto dto);
    void changeEmail(ChangeEmailUserDto dto);
    void changePassword(ChangePasswordUserDto dto);
}
