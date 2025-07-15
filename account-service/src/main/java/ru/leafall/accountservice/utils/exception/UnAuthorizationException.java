package ru.leafall.accountservice.utils.exception;

public class UnAuthorizationException extends RuntimeException {
    public UnAuthorizationException() {

    }

    public UnAuthorizationException(String message) {
        super(message);
    }
}
