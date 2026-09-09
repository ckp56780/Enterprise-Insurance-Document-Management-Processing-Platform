package com.aig.ocr_service.producer;

import com.aig.common.dto.AuditEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventProducer {

    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;

    public void publishAuditEvent(
            AuditEvent event) {

        kafkaTemplate.send(
                "audit-topic",
                event);
    }
}