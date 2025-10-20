package com.protobin.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/projects")
public class ProjectController {

    @GetMapping()
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello");
    }
}
