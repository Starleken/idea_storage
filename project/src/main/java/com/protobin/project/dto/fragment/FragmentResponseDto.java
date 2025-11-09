package com.protobin.project.dto.fragment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(title = "Фрагмент",
        description = "Поля получаемые с фрагмента")
public class FragmentResponseDto {
    @Schema(description = "Идентификатор фрагмента",
            example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;

    @Schema(description = "Название фрагмента", example = "Авторизация", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Краткое содержание фрагмента", example = "JWT-токены, логин, пароль, хеширование")
    private String shortDescription;

    @Schema(description = "Полное описание фрагмента", example = "На странице авторизации должны находиться логин...")
    private String description;

    @Schema(description = "Изображение для фрагмента", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc.jpg")
    private String image;

    @Schema(description = "Идентификатор человека, который отвечает за фрагмент", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    private UUID userId;

    @Schema(description = "Дата создания фрагмента", example = "1762672272", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long createdAt;

    @Schema(description = "Дата обновления фрагмента", example = "1762672272", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long updatedAt;
}
