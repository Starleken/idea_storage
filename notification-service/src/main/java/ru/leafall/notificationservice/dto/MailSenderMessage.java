package ru.leafall.notificationservice.dto;

import lombok.Data;

@Data
public class MailSenderMessage {
    private String mail;
    private String text;
}
