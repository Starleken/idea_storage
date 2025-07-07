package ru.leafall.mainservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.leafall.mainstarter.exception.BadRequestException;
import ru.leafall.mainstarter.exception.ErrorDto;
import ru.leafall.mainstarter.exception.NotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionProvider {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handleException(BadRequestException ex) {
        return handle(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {
        return handle(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(NotFoundException ex) {
        return handle(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return handle(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDto> handle(Exception ex, HttpStatus status) {
        log.error(ex.getMessage(), ex);
        var error = ErrorDto.builder().statusCode(status.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, status);
    }

}
