package ru.leafall.communityservice.exception;

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

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(NotFoundException ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handleException(BadRequestException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDto> handleException(Exception exception, HttpStatus status) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(ErrorDto.builder()
                .message(exception.getMessage())
                .statusCode(status.value())
                .build(), status);
    }
}