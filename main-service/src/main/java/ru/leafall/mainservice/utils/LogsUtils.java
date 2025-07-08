package ru.leafall.mainservice.utils;

import lombok.extern.slf4j.Slf4j;

public abstract class LogsUtils {

    public static String getLogs(Class entity, String methodName, Object dto) {
        return String.format("%s %s request: %s", entity, methodName, dto.toString());
    }

    public static String getLogs(Class entity, String methodName) {
        return String.format("%s %s requested", entity, methodName);
    }

    public static String getLogs(Class entity, String methodName, long count) {
        return String.format("In the %s entity, %d elements were found in the %s method", entity, count, methodName);
    }

    public static String getResponseLogs(Class entity, String operation, Object dto) {
        return String.format("%s operation: %s response: %s", entity, operation, dto.toString());
    }
}
