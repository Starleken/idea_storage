package com.protobin.project.exception;

public class ApiError extends RuntimeException {
    private final int status;
    public ApiError(int statusCode, String message) {
        super(message);
        this.status = statusCode;
    }

    public int getStatus() {
        return status;
    }
}
