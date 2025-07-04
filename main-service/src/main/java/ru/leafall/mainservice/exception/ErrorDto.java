package ru.leafall.mainservice.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {
    private Integer statusCode;
    private String message;
}
