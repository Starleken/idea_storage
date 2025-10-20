package com.protobin.project.exception;

public class ConflictException extends ApiError {
    public ConflictException(String message) {
        super(409, message);
    }
}
