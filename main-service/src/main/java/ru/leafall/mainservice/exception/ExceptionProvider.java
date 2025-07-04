package ru.leafall.mainservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionProvider {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handleException(BadRequestException ex) {
        log.error(ex.getMessage());
        ErrorDto dto = ErrorDto.builder().statusCode(400).message(ex.getMessage()).build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        ErrorDto dto = ErrorDto.builder().statusCode(400).message(ex.getMessage()).build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(NotFoundException ex) {
        log.error(ex.getMessage());
        ErrorDto dto = ErrorDto.builder().statusCode(404).message(ex.getMessage()).build();
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        log.error(ex.getMessage());
        ErrorDto dto = ErrorDto.builder().statusCode(500).message(ex.getMessage()).build();
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
