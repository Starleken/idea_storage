package ru.leafall.mainservice.component.communication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.leafall.mainservice.component.ProjectObservable;
import ru.leafall.mainservice.component.model.Participant;
import ru.leafall.mainservice.entity.ProjectEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommunicationService implements ProjectObservable {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void create(ProjectEntity project) {
        var uri = UriComponentsBuilder
                .fromUriString(String.format("http://lb:communication-service/v1/users/%d/projects/%d", 1, project.getId()))
                .build().toUri();
        var response = restTemplate.getForEntity(uri, Participant.class);
        var result = response.getBody();
    }

    @Override
    public void delete(ProjectEntity project) {
        var uri = UriComponentsBuilder
                .fromUriString(String.format("http://lb:communication-service/v1/users/%d/projects/%d", 1, project.getId()))
                .build().toUri();
        restTemplate.delete(uri);
    }
}