package com.aig.audit.kafka.consumer;

import com.aig.common.dto.AuditEvent;
import com.aig.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditConsumer {

    private final AuditService auditService;

    @KafkaListener(
            topics = "audit-events",
            groupId = "audit-group"
    )
    public void consume(AuditEvent event) {

        try {

            log.info("Received event : {}", event);

            auditService.saveAudit(event);

        } catch (Exception ex) {

            log.error(
                    "Error while processing audit event",
                    ex
            );
        }
    }
}