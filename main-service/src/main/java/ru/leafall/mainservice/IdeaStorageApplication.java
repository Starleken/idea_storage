package ru.leafall.mainservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Idea storage (main service)", version = "0.0.1-alpha"))
@EnableDiscoveryClient
public class IdeaStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeaStorageApplication.class, args);
    }

}
