package com.protobin.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDto {
    private Integer status;
    private List<String> errors;

}
