package com.aig.ocr_service.consumer;

import com.aig.common.dto.DocumentCreatedEvent;
import com.aig.ocr_service.service.impl.OcrServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RealOcrConsumer {

    private final OcrServiceImpl realOcrService;

    @KafkaListener(
            topics = "document-created-topic",
            groupId = "real-ocr-group")
    public void consume(
            DocumentCreatedEvent event) {

        log.info(
                "Real OCR Event Received : {}",
                event);

        realOcrService.processDocument(
                event);
    }
}