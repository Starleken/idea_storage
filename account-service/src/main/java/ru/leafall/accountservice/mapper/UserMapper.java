package ru.leafall.accountservice.mapper;

import org.mapstruct.Mapper;
import ru.leafall.accountservice.dto.user.UserCreateDto;
import ru.leafall.accountservice.dto.user.UserResponseDto;
import ru.leafall.accountservice.dto.user.UserUpdateDto;
import ru.leafall.accountservice.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapToResponse(UserEntity user);
    List<UserResponseDto> mapToResponse(List<UserEntity> users);
    UserEntity mapToEntity(UserCreateDto dto);
    default void update(UserEntity user, UserUpdateDto dto) {
        if(user == null || dto == null) {
            return;
        }
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
    }

}
