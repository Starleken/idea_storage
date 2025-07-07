package ru.leafall.fileservicestarter.dto;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import java.io.IOException;
import java.io.InputStream;

public class FileUploadDto extends ByteArrayResource {

    public FileUploadDto(byte[] byteArray) {
        super(byteArray);
    }
}
