package com.protobin.project.service.impl;

import com.protobin.project.exception.BadRequestException;
import com.protobin.project.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public void removeImage(String imageName) {
        var fileName = Paths.get("src", "main", "resources", "public", imageName);
        try {
            Files.deleteIfExists(fileName);

        } catch (IOException exception) {
            log.error("IOException: ", exception);
        }
    }

    @Override
    public String uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new BadRequestException("Image is empty");
        }

        if (image.getSize() > 5242880) { // 5 MB
            throw new BadRequestException("Image is so heavy");
        }
        var splitFileName = image.getOriginalFilename().split("\\.");
        var extension = "." + splitFileName[splitFileName.length - 1];
        var fileName = UUID.randomUUID() + extension;
        var dir = Paths.get("src", "main", "resources", "public");
        var file = new File(dir + "/" + fileName);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try(OutputStream os = new FileOutputStream(file)) {
            os.write(image.getBytes());
        }
        catch (IOException err) {
            throw new RuntimeException(err);
        }
        return fileName;
    }
}
