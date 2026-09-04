package com.aig.document.document_service.producer;

import com.aig.common.dto.AuditEvent;
import com.aig.document.document_service.event.DocumentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public DocumentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(DocumentCreatedEvent documentEvent,
                        AuditEvent auditEvent) {

        kafkaTemplate.send(
                "document-created-topic",
                documentEvent);

        log.info("DocumentCreatedEvent Published : {}", documentEvent);

        kafkaTemplate.send(
                "audit-events",
                auditEvent);

        log.info("AuditEvent Published : {}", auditEvent);
    }
}