package com.Notification.Service.consumer;

import com.Notification.Service.service.NotificationService;
import com.aig.common.dto.DocumentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventConsumerforDocumentUploaded {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "document-created-topic",
            groupId = "notification-group")
    public void consume(DocumentCreatedEvent event) {

        log.info("Received Event : {}", event);

        notificationService.sendNotification(event);
    }
}