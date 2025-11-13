package com.protobin.project.mapper;

import com.protobin.project.dto.boardElement.BoardElementCreateDto;
import com.protobin.project.dto.boardElement.BoardElementResponseDto;
import com.protobin.project.dto.boardElement.BoardElementUpdateDto;
import com.protobin.project.entity.BoardElementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardElementMapper {

    BoardElementResponseDto mapToDto(BoardElementEntity entity);

    BoardElementEntity mapToEntity(BoardElementCreateDto createDto);

    default void update(BoardElementEntity entity, BoardElementUpdateDto updateDto) {
        entity.setCoordinateX(updateDto.getCoordinateX());
        entity.setCoordinateY(updateDto.getCoordinateY());
        entity.setPayload(updateDto.getPayload());
    }
}
