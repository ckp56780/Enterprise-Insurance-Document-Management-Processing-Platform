package com.Notification.Service.service.impl;

import com.Notification.Service.service.NotificationService;
import com.aig.common.dto.DocumentCreatedEvent;
import com.aig.common.dto.OcrCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(DocumentCreatedEvent event) {

        log.info("Notification Sent For Document : {}",event.getDocumentName());
        System.out.println("EMAIL SENT => " + event.getDocumentName());
    }

    @Override
    public void sendOcrCompletionNotification(OcrCompletedEvent event) {
        log.info("OCR Completed Notification Sent for Document Id : {}", event.getDocumentId());
        log.info("Document Name : {}",event.getDocumentName());
        log.info("OCR Status : {}", event.getStatus());
    }
}