package ru.leafall.communityservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.leafall.communityservice.model.User;

import java.util.List;
import java.util.Set;


@Service
public class AccountService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<User> findAllByIds(Set<Long> ids) {
        var url = UriComponentsBuilder.fromUriString("http://lb:account-service/v1/users")
                .queryParam("ids", ids)
                .build().toUri();
        var response = restTemplate.getForEntity(url, List.class);
        return response.getBody();
    }
}
