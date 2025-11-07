package com.protobin.project.controller;

import com.protobin.project.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "Проверка работы сервера")
public class HealthCheckController {

    @GetMapping("v1/health")
    @Operation(
            summary = "Получить состояние сервера",
            description = "Если приходит ответ, значит API в исправном состоянии"
    )
    @ApiResponse(responseCode = "200", description = "Состояние сервера в исправном состоянии" )
    public ResponseEntity<ResponseDto> getHealthStatus() {
        return ResponseEntity.ok(new ResponseDto(200, "ok"));
    }


}
