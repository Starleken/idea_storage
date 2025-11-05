package com.protobin.authservice.mapper;

import com.protobin.authservice.dto.UserResponseDto;
import com.protobin.authservice.dto.UserSignupDto;
import com.protobin.authservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto mapToDto(UserEntity entity);

    @Mapping(target = "password", ignore = true)
    UserEntity mapToEntity(UserSignupDto signupDto);
}
