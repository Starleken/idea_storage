package com.protobin.project.controller;

import com.protobin.project.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("v1/health")
    public ResponseEntity<ResponseDto> getHealthStatus() {
        return ResponseEntity.ok(new ResponseDto(200, "ok"));
    }


}
