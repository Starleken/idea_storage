package com.protobin.project.exception;

public class ApiError extends Exception {
    private final int status;
    public ApiError(int statusCode, String message) {
        super(message);
        this.status = statusCode;
    }

    public int getStatus() {
        return status;
    }
}
