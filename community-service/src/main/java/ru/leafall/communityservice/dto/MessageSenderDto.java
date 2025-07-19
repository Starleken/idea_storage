package ru.leafall.communityservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MessageSenderDto {
    private String email;
    private String message;
}
