package com.protobin.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static com.protobin.authservice.utils.ExceptionUtils.*;

@RestControllerAdvice
@Slf4j
public class ExceptionProvider {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Error> handleException(EntityNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(toError(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalActionException.class})
    public ResponseEntity<Error> handleException(IllegalActionException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(toError(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handler(ExpiredJwtException ex, WebRequest request) {
        return new ResponseEntity<>("Token is expired", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TokenIsUsedException.class)
    public ResponseEntity<Object> handler(TokenIsUsedException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(toError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
