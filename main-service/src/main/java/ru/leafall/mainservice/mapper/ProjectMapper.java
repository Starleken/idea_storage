package ru.leafall.mainservice.mapper;

import ru.leafall.mainservice.dto.project.ProjectCreateDto;
import ru.leafall.mainservice.dto.project.ProjectFullDto;
import ru.leafall.mainservice.dto.project.ProjectUpdateDto;
import ru.leafall.mainservice.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectFullDto mapToFullDto(ProjectEntity entity);
    ProjectEntity mapToEntity(ProjectCreateDto dto);
    default void update(ProjectEntity project, ProjectUpdateDto dto) {
        project.setName(dto.getName());
        project.setIdea(dto.getIdea());
        project.setPrice(dto.getPrice());
        project.setShortDescription(dto.getShortDescription());
        project.setFullDescription(dto.getFullDescription());
        project.setFinishedAt(dto.getFinishedAt());
        project.setIsVisible(dto.getIsVisible());
        project.setExpiredAt(dto.getExpiredAt());
        project.setReasonForPurchase(dto.getReasonForPurchase());
    }
}
