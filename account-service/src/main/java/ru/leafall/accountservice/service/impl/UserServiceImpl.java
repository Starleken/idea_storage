package ru.leafall.accountservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leafall.accountservice.dto.token.TokenRefreshDto;
import ru.leafall.accountservice.dto.token.TokenResponseDto;
import ru.leafall.accountservice.dto.user.*;
import ru.leafall.accountservice.entity.Role;
import ru.leafall.accountservice.entity.UserEntity;
import ru.leafall.accountservice.mapper.UserMapper;
import ru.leafall.accountservice.repository.UserRepository;
import ru.leafall.accountservice.service.EncodingService;
import ru.leafall.accountservice.service.TokenService;
import ru.leafall.accountservice.service.UserService;
import ru.leafall.accountservice.utils.exception.UnAuthorizationException;
import ru.leafall.mainstarter.exception.NotFoundException;
import ru.leafall.mainstarter.utils.PaginationParams;
import ru.leafall.mainstarter.utils.PaginationResponse;
import ru.leafall.mainstarter.utils.ThrowableUtils;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EncodingService encodingService;
    private final TokenService tokenService;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse<UserResponseDto> findAll(PaginationParams params) {
        var sort = Sort.by("name").ascending();
        var pagination = PageRequest.of(params.page(), params.limit(), sort);
        var users = userRepository.findAll(pagination);
        var result = users.map(userMapper::mapToResponse);
        return new PaginationResponse<>(result.getContent(), result.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        var user = findUserByIdOrThrowNotFoundException(id);
        return userMapper.mapToResponse(user);
    }

    @Override
    @Transactional
    public TokenResponseDto signUp(UserCreateDto dto) {
        var foundUserByEmailOrLogin = userRepository.findByLoginOrEmail(dto.getLogin(), dto.getEmail());
        if (foundUserByEmailOrLogin.isPresent()) {
            throw ThrowableUtils.getBadRequestException("User with login `%s` or email `%s` is exists!", dto.getLogin(), dto.getEmail());
        }
        var hashPassword = encodingService.hash(dto.getPassword());
        dto.setPassword(hashPassword);
        var entity = userMapper.mapToEntity(dto);
        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_USER);
        entity.setRoles(roles);

        var result = userRepository.save(entity);

        return generateTokens(result);
    }

    @Override
    @Transactional
    public UserResponseDto update(UserUpdateDto dto) {
        var user = findUserByIdOrThrowNotFoundException(dto.getId());
        userMapper.update(user, dto);
        var result = userRepository.save(user);
        return userMapper.mapToResponse(result);
    }

    @Override
    @Transactional
    public UserResponseDto deleteById(Long id) {
        var user = findUserByIdOrThrowNotFoundException(id);
        userRepository.delete(user);
        return userMapper.mapToResponse(user);
    }

    @Override
    @Transactional
    public TokenResponseDto signIn(SignInDto dto) {
        var user = findUserByLoginOrThrowUnAuthorizationException(dto.getLogin());
        if (!encodingService.matches(dto.getPassword(), user.getPassword())) {
            throw new UnAuthorizationException();
        }

        return generateTokens(user);
    }

    @Override
    public TokenResponseDto refresh(TokenRefreshDto dto) {
        tokenService.validateRefreshToken(dto.getRefreshToken());
        var token = tokenService.generateAccessToken(dto.getRefreshToken());
        return TokenResponseDto.builder().accessToken(token).refreshToken(dto.getRefreshToken()).build();
    }

    @Override
    @Transactional
    public void logout(TokenRefreshDto dto) {
        tokenService.validateRefreshToken(dto.getRefreshToken());
        tokenService.deleteRefreshToken(dto.getRefreshToken());
    }

    @Override
    @Transactional
    public void changeEmail(ChangeEmailUserDto dto) {
        var foundUser = userRepository.findByLogin(dto.getLogin()).orElseThrow(()->
                ThrowableUtils.getNotFoundException("User with login `%s` is not found!", dto.getLogin()));

        if (userRepository.findByEmail(dto.getNewEmail()).isPresent()) {
            throw ThrowableUtils.getBadRequestException("User with email %s is exists");
        }

        foundUser.setEmail(dto.getNewEmail());
        userRepository.save(foundUser);
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordUserDto dto) {
        var foundUser = userRepository.findByEmail(dto.getEmail()).orElseThrow(()->
                ThrowableUtils.getNotFoundException("User with email `%s` is not found!", dto.getEmail()));
        var hashPassword = encodingService.hash(dto.getNewPassword());
        foundUser.setPassword(hashPassword);
        userRepository.save(foundUser);
    }

    private UserEntity findUserByIdOrThrowNotFoundException(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                ThrowableUtils.getNotFoundException("user with id %d is not found", id)
        );
    }

    private UserEntity findUserByLoginOrThrowUnAuthorizationException(String login) {
        return userRepository.findByLogin(login).orElseThrow(() ->
                ThrowableUtils.getBadRequestException("User with login or password is incorrect")
        );
    }

    private TokenResponseDto generateTokens(UserEntity result) {
        var refreshToken = tokenService.generateRefreshToken(result);
        var accessToken = tokenService.generateAccessToken(refreshToken);

        return TokenResponseDto.builder()
                .accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
