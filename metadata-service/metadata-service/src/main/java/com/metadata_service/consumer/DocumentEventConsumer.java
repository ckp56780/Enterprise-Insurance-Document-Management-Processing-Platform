package com.metadata_service.consumer;

import com.metadata_service.event.DocumentCreatedEvent;
import com.metadata_service.service.MetadataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentEventConsumer {

    private final MetadataService metadataService;

    @KafkaListener(
            topics = "document-created-topic",
            groupId = "metadata-group")
    public void consume(
            DocumentCreatedEvent event) {

        log.info(
                "Received Event : {}",
                event);

        metadataService.saveMetadata(event);
    }

//    @KafkaListener(
//            topics = "document-created-topic",
//            groupId = "metadata-group")
//    public void consume(String message) {
//
//        System.out.println("MESSAGE RECEIVED = " + message);
//    }
}