package ru.leafall.communityservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.leafall.communityservice.dto.MessageSenderDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderService {
    private final KafkaTemplate<String, MessageSenderDto> kafkaTemplate;

    private final String topicMessage = "messages_sender";

    public void sendMessage(MessageSenderDto dto) {
        kafkaTemplate.send(topicMessage, dto)
                .whenComplete((result, ex) ->{
                            if(ex == null) {
                                log.info(result.toString());
                            } else {
                                log.error(ex.getMessage(), ex);
                            }
                });
    }
}
