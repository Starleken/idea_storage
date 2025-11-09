package com.protobin.project.dto.project;

import com.protobin.project.entity.ProjectVisibleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Schema(title = "Создание проекта",
        description = "Объект для создания проекта")
public class ProjectCreateDto {
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
