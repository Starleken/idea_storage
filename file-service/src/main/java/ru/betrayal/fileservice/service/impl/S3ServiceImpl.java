package ru.betrayal.fileservice.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.TransferManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.fileservice.service.S3Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 client;
    private final TransferManager transferManager;

    @Override
    @SneakyThrows
    public String uploadFile(MultipartFile file, String keyName, String bucketName) {
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        var upload = transferManager.upload(bucketName, keyName, file.getInputStream(), metadata);

        try {
            upload.waitForCompletion();
        } catch (InterruptedException ex) {
            throw ex;
        }

        return keyName;
    }

    @Override
    public String getFileNameFromUrl(String fileUrl) {
        String fileName;

        try {
            String fileNameWithTicks = fileUrl.split("/")[1];
            fileName = fileNameWithTicks.split("_")[1];
        } catch (Exception ex) {
            throw new RuntimeException();
        }

        return fileName;
    }

    @Override
    public InputStream downloadFile(String bucketName, String keyName) {
        S3Object s3Object = client.getObject(bucketName, keyName);
        return s3Object.getObjectContent();
    }
}
