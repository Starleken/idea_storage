package com.protobin.project.controller;

import com.protobin.project.dto.boardElement.BoardElementCreateDto;
import com.protobin.project.dto.boardElement.BoardElementResponseDto;
import com.protobin.project.dto.boardElement.BoardElementUpdateDto;
import com.protobin.project.exception.annotation.ApiResponseBadRequest;
import com.protobin.project.exception.annotation.ApiResponseForbidden;
import com.protobin.project.exception.annotation.ApiResponseNoAuth;
import com.protobin.project.exception.annotation.ApiResponseNotFound;
import com.protobin.project.service.BoardElementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Board elements", description = "Необходимые руты для работы с элементами доски")
@RequiredArgsConstructor
@MessageMapping("/elements")
@Slf4j
public class BoardElementController {

    private final BoardElementService boardElementService;

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/project/{projectId}")
    @Operation(
            summary = "Получить список элементов по UUID проекта",
            description = "Получить список элементов",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "Список элементов доски проекта успешно получен")
    @ApiResponseBadRequest
    @ApiResponseNotFound
    @ApiResponseNoAuth
    @ApiResponseForbidden
    public ResponseEntity<List<BoardElementResponseDto>> findAllByProject(@PathVariable UUID projectId) {
        var response = boardElementService.findAllByProject(projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @MessageMapping("/create/project/{projectId}")
    public void create(BoardElementCreateDto createDto, @DestinationVariable UUID projectId) {
        var response = boardElementService.create(createDto);
        messagingTemplate.convertAndSend("/topic/element/create/project/" + projectId, response);
    }

    @MessageMapping("/update/project/{projectId}")
    public void update(BoardElementUpdateDto updateDto, @DestinationVariable UUID projectId) {
        var response = boardElementService.update(updateDto);

        messagingTemplate.convertAndSend("/topic/element/update/project/" + projectId, response);
    }

    @MessageMapping("/delete/project/{projectId}")
    public void deleteById(UUID id, @DestinationVariable UUID projectId) {
        boardElementService.deleteById(id);

        messagingTemplate.convertAndSend("/topic/element/delete/project/" + projectId, id);
    }

    @MessageMapping("/delete/all/project/{projectId}")
    public void deleteByIds(List<UUID> ids, @DestinationVariable UUID projectId) {
        boardElementService.deleteByIds(ids);

        messagingTemplate.convertAndSend("/topic/element/delete/all/project/" + projectId, "DELETED");
    }
}
