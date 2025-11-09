package com.protobin.project.dto.project;

import com.protobin.project.entity.ProjectVisibleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(title = "Проект",
        description = "Поля которые отдаются")
public class ProjectResponseDto {

    @Schema(description = "Идентификатор пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "Заголовок", example = "VoiceTok")
    private String title;

    @Schema(description = "Краткое описание идеи",
            example = "Приложение для разговора с котятами")
    private String concept;

    @Schema(description = "Подробное описание идеи",
            example = "Приложение где ваш кот будет управлять вами полностью согласуя ваши задачи, к примеру... и т.д.")
    private String description;

    @Schema(description = "Статус видимости проекта",
            example = "VISIBLE")
    private ProjectVisibleStatus visibleStatus;
}
