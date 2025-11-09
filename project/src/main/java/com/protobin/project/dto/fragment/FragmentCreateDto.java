package com.protobin.project.dto.fragment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Schema(title = "Создание фрагмента",
        description = "Поля необходимые для создания")
public class FragmentCreateDto {
    @Schema(description = "Идентификатор проекта", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID projectId;

    @Schema(description = "Название фрагмента", example = "Авторизация", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Краткое содержание фрагмента", example = "JWT-токены, логин, пароль, хеширование")
    private String shortDescription;

    @Schema(description = "Полное описание фрагмента", example = "На странице авторизации должны находиться логин...")
    private String description;

    @Schema(description = "Изображение фрагмента")
    private MultipartFile image;
}
