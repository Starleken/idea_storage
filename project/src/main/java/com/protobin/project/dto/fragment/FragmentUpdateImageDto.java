package com.protobin.project.dto.fragment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Schema(title = "Обновление изображения фрагмента",
        description = "Поля необходимые для обновления изображения")
public class FragmentUpdateImageDto {
    @Schema(description = "Изображение", requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile image;
}
