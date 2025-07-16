package ru.leafall.accountservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.leafall.accountservice.utils.exception.UnAuthorizationException;
import ru.leafall.mainstarter.exception.BadRequestException;
import ru.leafall.mainstarter.exception.ErrorDto;
import ru.leafall.mainstarter.exception.NotFoundException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class ExceptionProvider {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        return handle(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(NotFoundException exception) {
        return handle(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorDto> handleException(BadRequestException exception) {
        return handle(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnAuthorizationException.class})
    public ResponseEntity<ErrorDto> handleException(UnAuthorizationException exception) {
        return handle(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException exception) {
        return handle(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorDto> handleException(AccessDeniedException exception) {
        return handle(exception, HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<ErrorDto> handle(Exception ex, HttpStatus status) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorDto.builder()
                .message(ex.getMessage())
                .statusCode(status.value())
                .build(), status);
    }
}
