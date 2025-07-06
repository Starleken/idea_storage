package ru.leafall.fileservicestarter.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.leafall.fileservicestarter.service.FileService;

@Configuration
@EnableConfigurationProperties(FileServiceMapProperties.class)
public class FileServiceMapAutoConfiguration {

    @Bean
    @ConditionalOnBean
    public FileService fileService(FileServiceMapProperties props) {
        return new FileService(props.getFileServiceUrl());
    }
}
