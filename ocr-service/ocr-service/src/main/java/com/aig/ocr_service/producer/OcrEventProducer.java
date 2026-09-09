package com.aig.ocr_service.producer;

import com.aig.common.dto.OcrCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OcrEventProducer {

    private final KafkaTemplate<
            String,OcrCompletedEvent> kafkaTemplate;

    public void publishOcrCompletedEvent(OcrCompletedEvent event) {

        kafkaTemplate.send(
                "ocr-completed-topic",event);
    }
}