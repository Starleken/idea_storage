package ru.leafall.mainstarter.utils;

public record PaginationParams(Integer limit, Integer page) {
    public static final String LIMIT = "_limit";
    public static final String PAGE = "_page";
    public static final String HEADER_TOTAL_COUNT = "total-count";

    public Integer getOffset() {
        return limit * page;
    }
}
