package com.Notification.Service.service.impl;

import com.Notification.Service.event.DocumentCreatedEvent;
import com.Notification.Service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl
        implements NotificationService {

    @Override
    public void sendNotification(
            DocumentCreatedEvent event) {

        log.info(
                "Notification Sent For Document : {}",
                event.getDocumentName());

        System.out.println(
                "EMAIL SENT => " + event.getDocumentName());
    }
}