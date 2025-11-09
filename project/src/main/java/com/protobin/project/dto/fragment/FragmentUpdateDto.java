package com.protobin.project.dto.fragment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@Schema(title = "Обновление фрагмента",
        description = "Поля необходимые для обновления")
public class FragmentUpdateDto {
    @Schema(description = "Название фрагмента", example = "Авторизация")
    @Length(min = 1, max = 50, message = "Заголовок может быть от 1 до 50 символов")
    private String title;

    @Schema(description = "Краткое содержание фрагмента", example = "JWT-токены, логин, пароль, хеширование")
    @Length(max = 500, message = "Краткое описание может быть до 500 символов")
    private String shortDescription;

    @Schema(description = "Полное описание фрагмента", example = "На странице авторизации должны находиться логин...")
    @Length(max = 10000, message = "Полное описание может быть до 10000 символов")
    private String description;

    @Schema(description = "Идентификатор человека, который отвечает за фрагмент", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc")
    private UUID userId;
}
