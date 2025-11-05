package com.protobin.authservice.utils;

import com.protobin.authservice.exception.EntityNotFoundException;
import com.protobin.authservice.exception.Error;
import com.protobin.authservice.exception.IllegalActionException;
import com.protobin.authservice.exception.TokenIsUsedException;

public abstract class ExceptionUtils {

    public static EntityNotFoundException getEntityNotFoundException(Class clazz) {
        return new EntityNotFoundException(clazz.getSimpleName() + " is not found");
    }

    public static void throwIllegalActionException(String message) {
        throw new IllegalActionException(message);
    }

    public static Error toError(Exception ex) {
        Error error = new Error();
        error.getErrors().add(ex.getMessage());

        return error;
    }

    public static void throwTokenIsUsedException(String errorMessage) {
        throw new TokenIsUsedException(errorMessage);
    }
}
