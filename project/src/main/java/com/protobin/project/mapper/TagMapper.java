package com.protobin.project.mapper;

import com.protobin.project.dto.tag.TagResponseDto;
import com.protobin.project.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagResponseDto mapToDto(TagEntity tag);
}
