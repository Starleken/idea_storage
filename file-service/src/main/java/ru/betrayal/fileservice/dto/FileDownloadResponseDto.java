package ru.betrayal.fileservice.dto;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Data
public class FileDownloadResponseDto {
    private ByteArrayOutputStream outputStream;
    private String fileName;
}
