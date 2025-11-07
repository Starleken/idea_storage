package com.protobin.project.exception.annotation;

import com.protobin.project.dto.ResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "404", description = "Ресурс был не найден",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResponseDto.class)) )
public @interface ApiResponseNotFound {
}
