package ru.betrayal.fileservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FileResponseDto {
    private UUID id;
    private String fileName;
    private String url;
    private Long createdAt;
}
