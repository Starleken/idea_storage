package ru.betrayal.fileservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface S3Service {
    String uploadFile(MultipartFile file, String keyName, String bucketName);
    String getFileNameFromUrl(String fileUrl);
    InputStream downloadFile(String bucketName, String keyName);
}
