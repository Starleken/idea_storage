package ru.betrayal.fileservice.mapper;

import org.mapstruct.Mapper;
import ru.betrayal.fileservice.dto.FileResponseDto;
import ru.betrayal.fileservice.entity.FileEntity;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResponseDto mapToResponseDto(FileEntity file);
    FileEntity mapToEntity(String url, String fileName);
}
