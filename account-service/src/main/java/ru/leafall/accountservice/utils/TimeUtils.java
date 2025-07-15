package ru.leafall.accountservice.utils;

import java.time.Instant;
import java.util.Date;

public class TimeUtils {

    public static Long getTimeFromUtcExpired(long minutesExpired) {
        var seconds = convertMinutesToSeconds(minutesExpired);
        return Instant.now().plusSeconds(seconds).toEpochMilli();
    }

    public static Date getDateFromUtcExpired(long minutesExpired) {
        var seconds = convertMinutesToSeconds(minutesExpired);
        var expiredTime = Instant.now().plusSeconds(seconds);
        return Date.from(expiredTime);
    }

    public static Long getCurrentTimeFromUTC() {
        return Instant.now().toEpochMilli();
    }

    private static long convertMinutesToSeconds(long minutes) {
        return minutes * 60;
    }
}
