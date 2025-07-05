package ru.leafall.mainstarter.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        this("Object not found");
    }
}
