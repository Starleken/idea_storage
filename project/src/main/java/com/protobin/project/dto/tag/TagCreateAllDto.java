package com.protobin.project.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@Schema(title = "Создание тэгов",
        description = "Поля необходимые для создания")
public class TagCreateAllDto {

    @Schema(description = "Список тэгов")
    @NotEmpty
    private List<String> names;


    @Schema(description = "Идентификатор проекта", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    @org.hibernate.validator.constraints.UUID
    private UUID projectId;
}
