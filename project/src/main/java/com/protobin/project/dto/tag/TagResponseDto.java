package com.protobin.project.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(title = "Тэг",
        description = "Поля получаемые с тэга")
public class TagResponseDto {

    @Schema(description = "Идентификатор тэга", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    private UUID id;

    @Schema(description = "Значение тэга", example = "tag")
    private String name;
}
