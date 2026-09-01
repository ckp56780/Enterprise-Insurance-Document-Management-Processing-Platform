package com.Notification.Service.service;

import com.Notification.Service.event.DocumentCreatedEvent;

public interface NotificationService {

    void sendNotification(DocumentCreatedEvent event);
}