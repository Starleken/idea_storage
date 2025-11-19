package com.protobin.project.dto.boardElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.entity.enums.BoardElementEntityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(title = "Элемент доски",
        description = "Данные об элементе доски")
public class BoardElementResponseDto {

    @Schema(description = "Идентификатор элемента", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    private UUID id;

    @Schema(description = "Тип элемента", example = "FRAGMENT")
    private BoardElementEntityType type;

    @Schema(description = "Координата x элемента", example = "15")
    private float coordinateX;

    @Schema(description = "Координата y элемента", example = "15")
    private float coordinateY;

    @Schema(description = "Id элемента на доске", example = "some value")
    private String entityId;

    @Schema(description = "Полезная информация элемента", example = "{}")
    private JsonNode payload;

    @Schema(description = "Дата создания элемента в миллисекундах", example = "1763038915404")
    private Long createdAt;

    @Schema(description = "Дата обновления элемента в миллисекундах", example = "1763038915404")
    private Long updatedAt;
}
