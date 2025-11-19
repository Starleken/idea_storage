package com.protobin.project.dto.boardElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.protobin.project.entity.ProjectEntity;
import com.protobin.project.entity.enums.BoardElementEntityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(title = "Создание элемента доски",
        description = "Поля необходимые для создания")
public class BoardElementCreateDto {

    @Schema(description = "Идентификатор проекта", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    @org.hibernate.validator.constraints.UUID
    private UUID projectId;

    @Schema(description = "Тип элемента", example = "FRAGMENT")
    @NotNull(message = "Элемент обязан иметь тип")
    private BoardElementEntityType type;

    @Schema(description = "Координата x элемента", example = "15")
    @NotNull(message = "Элемент обязан иметь координату X")
    private float coordinateX;

    @Schema(description = "Координата y элемента", example = "15")
    @NotNull(message = "Элемент обязан иметь координату Y")
    private float coordinateY;

    @Schema(description = "Id элемента на доске", example = "some value")
    @NotBlank(message = "Элемент обязан иметь id на доске")
    private String entityId;

    @Schema(description = "Полезная информация элемента", example = "{}")
    @NotNull(message = "Элемент обязан иметь полезную информацию")
    private JsonNode payload;
}
