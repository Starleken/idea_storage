package com.protobin.project.mapper;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.project.ProjectUpdateDto;
import com.protobin.project.entity.ProjectEntity;
import org.mapstruct.Mapper;

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
