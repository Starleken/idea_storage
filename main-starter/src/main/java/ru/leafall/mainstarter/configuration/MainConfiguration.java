package ru.leafall.mainstarter.configuration;

import org.springframework.context.annotation.Configuration;
import ru.leafall.mainstarter.service.ProjectService;

@Configuration
public class MainConfiguration {

    public ProjectService projectService() {
        return new ProjectService();
    }
}
