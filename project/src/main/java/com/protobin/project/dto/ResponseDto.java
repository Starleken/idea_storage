package com.protobin.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(title = "Ответ",
    description = "Стандартизированный ответ от API")
public class ResponseDto {
    @Schema(title = "Статус", description = "Статус ответа", example = "200")
    private Integer status;

    @Schema(title = "Сообщение", description = "Сообщение ответа", example = "Какой-либо ответ")
    private String message;
}
