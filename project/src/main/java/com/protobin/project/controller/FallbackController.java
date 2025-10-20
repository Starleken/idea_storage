package com.protobin.project.controller;

import com.protobin.project.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/**")
    public ResponseEntity<ResponseDto> handleNotFound() {
        var body = new ResponseDto(404, "Page is not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
