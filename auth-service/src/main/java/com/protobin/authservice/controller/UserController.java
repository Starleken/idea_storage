package com.protobin.authservice.controller;

import com.protobin.authservice.dto.*;
import com.protobin.authservice.dto.token.RefreshTokenRequestDto;
import com.protobin.authservice.dto.token.RefreshTokenResponseDto;
import com.protobin.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Получить пользователя по UUID",
            description = "Возвращает пользователя по UUID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно возвращён"),
            @ApiResponse(responseCode = "403", description = "Отсутствует или неверный токен"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable UUID id) {
        var found = userService.findById(id);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @Operation(
            summary = "Регистрация пользователя",
            description = "Регистрирует пользователя в систему"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Почта или логин уже заняты"),
    })
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserSignupDto signupDto) {
        userService.signup(signupDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "Возвращает токены пользователю"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно авторизован"),
            @ApiResponse(responseCode = "400", description = "Неверный пароль"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto,
                                                  HttpServletRequest request) {
        var login = userService.login(loginDto, request);

        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление токенов",
            description = "Возвращает новую пару токенов пользователю"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Токены успешно обновлены"),
            @ApiResponse(responseCode = "403", description = "Неверный токен"),
            @ApiResponse(responseCode = "404", description = "Токен не найден"),
    })
    @PostMapping("/tokens/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshDto,
                                                           HttpServletRequest request) {
        var refresh = userService.refresh(refreshDto, request);

        return new ResponseEntity<>(refresh, HttpStatus.OK);
    }

    @Operation(
            summary = "Смена пароля пользователя",
            description = "Меняет пароль пользователя на новый",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пароль успешно изменён"),
            @ApiResponse(responseCode = "403", description = "Отсутствует или неверный токен"),
            @ApiResponse(responseCode = "400", description = "Старый пароль не совпадает"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDto passwordDto) {
        userService.changePassword(passwordDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Смена почты пользователя",
            description = "Меняет почту пользователя на новую",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Почта успешно изменена"),
            @ApiResponse(responseCode = "403", description = "Отсутствует или неверный токен"),
            @ApiResponse(responseCode = "400", description = "Почта уже существует"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/email")
    public ResponseEntity<Void> changeEmail(@RequestBody ChangeEmailDto emailDto) {
        userService.changeEmail(emailDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Выход пользователя из системы",
            description = "Удаляет токен пользователя",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Выход пользователя успешно произведён"),
            @ApiResponse(responseCode = "403", description = "Отсутствует или неверный токен"),
            @ApiResponse(responseCode = "404", description = "Токен не найден")
    })
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutDto) {
        userService.logout(logoutDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Удаляет пользователя из системы",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удалён"),
            @ApiResponse(responseCode = "403", description = "Отсутствует или неверный токен"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
