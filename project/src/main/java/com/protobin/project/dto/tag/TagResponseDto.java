package com.protobin.project.dto.tag;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TagResponseDto {

    private UUID id;
    private String name;
}
