package ru.leafall.mainstarter.utils;

import java.util.List;

public record PaginationResponse<T> (List<T> items, Long totalCount) {
}
