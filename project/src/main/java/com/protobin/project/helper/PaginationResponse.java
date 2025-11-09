package com.protobin.project.helper;


import java.util.List;

public record PaginationResponse<T>(List<T> items, Long total) {
}
