package com.aig.document.document_service.producer;
import lombok.RequiredArgsConstructor;
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
    public <DocumentCreatedEvent> void publish(DocumentCreatedEvent event) {

        kafkaTemplate.send(
                "document-created-topic",
                event);

        log.info("Document Created Event Published : {}", event);
    }
}