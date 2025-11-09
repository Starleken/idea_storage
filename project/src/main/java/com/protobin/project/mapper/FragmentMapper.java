package com.protobin.project.mapper;

import com.protobin.project.dto.fragment.FragmentCreateDto;
import com.protobin.project.dto.fragment.FragmentResponseDto;
import com.protobin.project.dto.fragment.FragmentUpdateDto;
import com.protobin.project.entity.FragmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FragmentMapper {
    FragmentResponseDto mapToDto(FragmentEntity project);
    @Mapping(target = "image", ignore = true)
    FragmentEntity mapToEntity(FragmentCreateDto dto);
    default void mapToEntity(FragmentEntity entity, FragmentUpdateDto updateDto) {
        entity.setTitle(updateDto.getTitle());
        entity.setDescription(updateDto.getDescription());
        entity.setShortDescription(updateDto.getShortDescription());
        entity.setDescription(updateDto.getDescription());
    }
}
