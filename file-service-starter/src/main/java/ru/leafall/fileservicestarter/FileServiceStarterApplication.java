package ru.leafall.fileservicestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.leafall.fileservicestarter.configuration.FileServiceMapAutoProps;

@SpringBootApplication
public class FileServiceStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileServiceStarterApplication.class, args);
    }

}
