package com.protobin.project.dto.fragment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(title = "Обновление фрагмента",
        description = "Поля необходимые для обновления")
public class FragmentUpdateDto {
    @Schema(description = "Название фрагмента", example = "Авторизация")
    private String title;

    @Schema(description = "Краткое содержание фрагмента", example = "JWT-токены, логин, пароль, хеширование")
    private String shortDescription;

    @Schema(description = "Полное описание фрагмента", example = "На странице авторизации должны находиться логин...")
    private String description;

    @Schema(description = "Идентификатор человека, который отвечает за фрагмент", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    private UUID userId;
}
