package com.protobin.project.exception;

public class NotFoundException extends ApiError {
    public NotFoundException(String message) {
        super(404, message);
    }
}
