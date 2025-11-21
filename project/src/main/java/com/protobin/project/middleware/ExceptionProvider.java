package com.protobin.project.middleware;

import com.protobin.project.dto.ErrorDto;
import com.protobin.project.dto.ResponseDto;
import com.protobin.project.exception.ApiError;
import com.protobin.project.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class ExceptionProvider {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDto> handleException(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                new ResponseDto(404, "Entity is not found"),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDto> handleException(NoResourceFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                new ResponseDto(404, "Page is not found"),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleInvalidUUID(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(new ErrorDto(400, errors) ,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleInvalidUUID(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() != null && ex.getRequiredType().equals(UUID.class)) {
            return ResponseEntity.badRequest().body("Invalid UUID format: " + ex.getValue());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

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
