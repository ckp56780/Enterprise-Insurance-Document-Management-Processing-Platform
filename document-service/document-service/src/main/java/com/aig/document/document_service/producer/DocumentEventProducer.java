package com.aig.document.document_service.producer;

import com.aig.common.dto.AuditEvent;

//below from a common library
import com.aig.common.dto.*;

import com.aig.common.dto.DocumentDeletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentEventProducer {

    //this is constructor-based dependency injection
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public DocumentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    //this publishes method will publish topic to all services
    public void publishDocumentCreatedEvent(DocumentCreatedEvent documentEvent) {

        kafkaTemplate.send(
                "document-created-topic",
                documentEvent);

        log.info("DocumentCreatedEvent Published : {}", documentEvent);
    }

    //this is for delete-for all services
    public void publishDocumentDeletedEvent(
            DocumentDeletedEvent documentEvent) {

        kafkaTemplate.send(
                "document-deleted-topic",
                documentEvent);

        log.info("DocumentDeletedEvent Published : {}", documentEvent);
    }

    public void publishAuditEvent(
            AuditEvent auditEvent) {

        kafkaTemplate.send(
                "audit-events",
                auditEvent);

        log.info("AuditEvent Published : {}", auditEvent);
    }
}