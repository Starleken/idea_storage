package ru.leafall.mainstarter.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.leafall.mainstarter.model.Project;

import java.util.List;
import java.util.Set;

public class ProjectService {
    private final RestTemplate restTemplate;
    private final String urlString = "http://lb:idea-storage";

    public ProjectService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Project> findAllByIds(Set<Long> ids) {
        try {
            String url = String.format("%s/%s", urlString, "v1/projects");
            var uri = UriComponentsBuilder.fromUriString(url)
                    .queryParam("ids", ids)
                    .queryParam("_page", 0)
                    .queryParam("_limit", ids.size())
                    .build()
                    .toUri();
            var response = restTemplate.getForEntity(uri, List.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
