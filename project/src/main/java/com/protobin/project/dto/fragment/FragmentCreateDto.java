package com.protobin.project.dto.fragment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Schema(title = "Создание фрагмента",
        description = "Поля необходимые для создания")
public class FragmentCreateDto {
    @Schema(description = "Идентификатор проекта", example = "3d5f1a26-c554-4bfe-811a-0e9ef2ed52fc", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID projectId;

    @Schema(description = "Название фрагмента", example = "Авторизация")
    @NotEmpty(message = "Заголовок не может быть пустым")
    @Length(min = 1, max = 50, message = "Заголовок может быть от 1 до 50 символов")
    private String title;

    @Schema(description = "Краткое содержание фрагмента", example = "JWT-токены, логин, пароль, хеширование")
    @Length(max = 500, message = "Краткое описание может быть до 500 символов")
    private String shortDescription;

    @Schema(description = "Полное описание фрагмента", example = "На странице авторизации должны находиться логин...")
    @Length(max = 10000, message = "Полное описание может быть до 10000 символов")
    private String description;

    @Schema(description = "Изображение фрагмента")
    private MultipartFile image;
}
