package com.protobin.project.dto.boardElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.entity.enums.BoardElementEntityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(title = "Обновление элемента доски",
        description = "Поля необходимые для обновления")
public class BoardElementUpdateDto {

    @Schema(description = "Идентификатор элемента", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    @org.hibernate.validator.constraints.UUID
    private UUID id;

    @Schema(description = "Координата x элемента", example = "15")
    @NotNull(message = "Элемент обязан иметь координату X")
    private float coordinateX;

    @Schema(description = "Координата y элемента", example = "15")
    @NotNull(message = "Элемент обязан иметь координату Y")
    private float coordinateY;

    @Schema(description = "Полезная информация элемента", example = "{}")
    @NotNull(message = "Элемент обязан иметь полезную информацию")
    private JsonNode payload;
}
