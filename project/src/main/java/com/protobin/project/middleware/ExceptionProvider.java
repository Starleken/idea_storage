package com.protobin.project.middleware;

import com.protobin.project.dto.ResponseDto;
import com.protobin.project.exception.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionProvider {

    @ExceptionHandler({ApiError.class})
    public ResponseEntity<ResponseDto> handleException(ApiError exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                new ResponseDto(exception.getStatus(), exception.getMessage()),
                HttpStatusCode.valueOf(exception.getStatus())
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDto> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                new ResponseDto(500, "Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
