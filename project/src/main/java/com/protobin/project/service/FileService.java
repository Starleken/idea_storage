package com.protobin.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void removeImage(String imageName);
    String uploadImage(MultipartFile image);
}
