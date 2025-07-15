package ru.leafall.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.leafall.accountservice.dto.token.TokenRefreshDto;
import ru.leafall.accountservice.dto.token.TokenResponseDto;
import ru.leafall.accountservice.dto.user.*;
import ru.leafall.accountservice.service.UserService;
import ru.leafall.mainstarter.utils.PaginationParams;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/users")
public class UserController {

    private final UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> findAll(
            @RequestParam(name = PaginationParams.PAGE, defaultValue = "0") Integer page,
            @RequestParam(name = PaginationParams.LIMIT, defaultValue = "10") Integer limit
    ) {
        var pagination = new PaginationParams(limit, page);
        var result = service.findAll(pagination);
        return ResponseEntity.ok()
                .header(PaginationParams.HEADER_TOTAL_COUNT, result.totalCount().toString())
                .body(result.items());
    }

    @GetMapping("/{id}")
    @Secured("USER")
    @PreAuthorize("hasRole('ADMIN') || authentication.principal.id==#id")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        var user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDto> signIn(@RequestBody SignInDto dto) {
        var user = service.signIn(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenResponseDto> signUp(@RequestBody UserCreateDto dto) {
        var user = service.signUp(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    @Secured("USER")
    @PreAuthorize("hasRole('ADMIN') || authentication.principal.id==#dto.getId()")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserUpdateDto dto) {
        var user = service.update(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody TokenRefreshDto dto) {
        var user = service.refresh(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestBody TokenRefreshDto dto) {
        service.logout(dto);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || authentication.principal.id==#id")
    public ResponseEntity<UserResponseDto> deleteById(@PathVariable Long id) {
        var user = service.deleteById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/change/email")
    public ResponseEntity<Boolean> changeEmail(@RequestBody ChangeEmailUserDto dto) {
        service.changeEmail(dto);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PatchMapping("/change/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordUserDto dto) {
        service.changePassword(dto);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
