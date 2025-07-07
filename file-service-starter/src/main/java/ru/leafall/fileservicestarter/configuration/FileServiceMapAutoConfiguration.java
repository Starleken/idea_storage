package ru.leafall.fileservicestarter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.leafall.fileservicestarter.service.FileService;

@Configuration
@EnableConfigurationProperties(FileServiceMapAutoProps.class)
public class FileServiceMapAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FileService fileService(FileServiceMapAutoProps props) {
        return new FileService(props.getFileServiceUrl());
    }
}
