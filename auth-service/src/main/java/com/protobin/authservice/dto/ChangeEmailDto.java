package com.protobin.authservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChangeEmailDto {

    private UUID id;
    private String newEmail;
}
