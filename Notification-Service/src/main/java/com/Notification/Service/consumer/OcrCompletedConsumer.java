package com.Notification.Service.consumer;

import com.Notification.Service.service.NotificationService;
import com.aig.common.dto.OcrCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OcrCompletedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "ocr-completed-topic",
            groupId = "notification-group")
    public void consume(OcrCompletedEvent event) {

        notificationService.sendOcrCompletionNotification(event);
    }
}