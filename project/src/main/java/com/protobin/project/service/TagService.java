package com.protobin.project.service;

import com.protobin.project.dto.tag.TagCreateAllDto;
import com.protobin.project.dto.tag.TagResponseDto;

import java.util.List;
import java.util.UUID;

public interface TagService {

    List<TagResponseDto> createAll(TagCreateAllDto createDtos);

    void deleteById(UUID id);
}
