package ru.leafall.mainservice.controllers;

import ru.leafall.mainservice.entity.TechnologyEntity;
import ru.leafall.mainservice.repository.TechnologyRepository;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectController {


    @GetMapping()
    public List<TechnologyEntity> getProjects(@RequestParam(name = "_limit", defaultValue = "10") @Min(0) Integer limit,
                                              @RequestParam(name = "_page", defaultValue = "1") @Min(1) Integer page) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnologyEntity create(@RequestParam() String name) {
        return null;
    }
}
