package com.protobin.project.dto.tag;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@Builder
public class TagCreateAllDto {

    private List<String> names;
    private UUID projectId;
}
