package ru.leafall.fileservicestarter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file-service-starter")
public class FileServiceMapProperties {
    private String fileServiceUrl;



    public String getFileServiceUrl() {
        return fileServiceUrl;
    }

    public void setFileServiceUrl(String fileServiceUrl) {
        this.fileServiceUrl = fileServiceUrl;
    }
}
