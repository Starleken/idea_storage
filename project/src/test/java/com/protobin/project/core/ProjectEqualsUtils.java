package com.protobin.project.core;

import com.protobin.project.dto.project.ProjectCreateDto;
import com.protobin.project.dto.project.ProjectResponseDto;
import com.protobin.project.dto.project.ProjectUpdateDto;
import com.protobin.project.entity.ProjectEntity;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ProjectEqualsUtils {

    public static void equals(ProjectResponseDto responseDto, ProjectEntity entity) {
        assertEquals(entity.getId(), responseDto.getId());
        assertEquals(entity.getTitle(), responseDto.getTitle());
        assertEquals(entity.getConcept(), responseDto.getConcept());
        assertEquals(entity.getDescription(), responseDto.getDescription());
        assertEquals(entity.getVisibleStatus(), responseDto.getVisibleStatus());
    }

    public static void equals(ProjectResponseDto responseDto, ProjectCreateDto createDto) {
        assertEquals(createDto.getTitle(), responseDto.getTitle());
        assertEquals(createDto.getDescription(), responseDto.getDescription());
        assertEquals(createDto.getConcept(), responseDto.getConcept());
        assertEquals(createDto.getVisibleStatus(), responseDto.getVisibleStatus());
    }

    public static void equals(ProjectResponseDto responseDto, ProjectUpdateDto updateDto) {
        assertEquals(updateDto.getId(), responseDto.getId());
        assertEquals(updateDto.getTitle(), responseDto.getTitle());
        assertEquals(updateDto.getDescription(), responseDto.getDescription());
        assertEquals(updateDto.getConcept(), responseDto.getConcept());
        assertEquals(updateDto.getVisibleStatus(), responseDto.getVisibleStatus());
    }
}
