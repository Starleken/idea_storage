package ru.leafall.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.leafall.notificationservice.dto.MailSenderMessage;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final MailSender mailSender;

    @KafkaListener(topics = "messages_sender", groupId = "notification-service")
    public void sendMails(@Payload MailSenderMessage message) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setFrom("vogistv@gmail.com");
        simpleMail.setText(message.getText());
        simpleMail.setTo(message.getMail());
        mailSender.send(simpleMail);
    }
}
