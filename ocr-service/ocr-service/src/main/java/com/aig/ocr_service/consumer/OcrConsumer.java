package com.aig.ocr_service.consumer;

import com.aig.common.dto.DocumentCreatedEvent;
import com.aig.ocr_service.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
@Slf4j
public class OcrConsumer {

    private final OcrService ocrService;

    @KafkaListener(
            topics = "document-created-topic",
            groupId = "ocr-group")
    public void consume(DocumentCreatedEvent event) {

        log.info(
                "OCR Service Received Event : {}",
                event);

        ocrService.processDocument(event);
    }
}