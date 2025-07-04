package ru.leafall.mainservice.utils;

import ru.leafall.mainservice.exception.BadRequestException;
import ru.leafall.mainservice.exception.NotFoundException;

public class ThrowableUtils {

    public static NotFoundException getNotFoundException() {
        return new NotFoundException();
    }

    public static NotFoundException getNotFoundException(String message, Object... args) {
        return new NotFoundException(String.format(message, args));
    }

    public static BadRequestException getBadRequestException() {
        return new BadRequestException();
    }

    public static BadRequestException getBadRequestException(String message, Object... args) {
        return new BadRequestException(String.format(message, args));
    }
}
