package com.protobin.project.mapper;

import com.protobin.project.dto.ProjectCreateDto;
import com.protobin.project.dto.ProjectResponseDto;
import com.protobin.project.dto.ProjectUpdateDto;
import com.protobin.project.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponseDto mapToDto(ProjectEntity entity);

    ProjectEntity mapToEntity(ProjectCreateDto createDto);

    default void update(ProjectEntity entity, ProjectUpdateDto updateDto) {
        entity.setTitle(updateDto.getTitle());
        entity.setDescription(updateDto.getDescription());
        entity.setConcept(updateDto.getConcept());
        entity.setVisibleStatus(updateDto.getVisibleStatus());
    }
}
