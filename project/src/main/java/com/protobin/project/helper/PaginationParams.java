package com.protobin.project.helper;


public record PaginationParams(Integer limit, Integer page) {

    public PaginationParams {
        if (limit > 100) {
            limit = 100;
        } else if (limit <= 0) {
            limit = 1;
        }
        if (page <= 0) {
            page = 0;
        }
    }
}
