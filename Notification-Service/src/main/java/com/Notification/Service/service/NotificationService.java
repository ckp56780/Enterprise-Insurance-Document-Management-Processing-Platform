package com.Notification.Service.service;

import com.aig.common.dto.DocumentCreatedEvent;
import com.aig.common.dto.OcrCompletedEvent;

public interface NotificationService {

    void sendNotification(DocumentCreatedEvent event);
    void sendOcrCompletionNotification(OcrCompletedEvent event);
}