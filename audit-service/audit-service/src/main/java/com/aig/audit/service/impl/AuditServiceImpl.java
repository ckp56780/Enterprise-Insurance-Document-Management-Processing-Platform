package com.aig.audit.service.impl;

import com.aig.audit.entity.AuditLog;
import com.aig.audit.repository.AuditLogRepository;
import com.aig.audit.service.AuditService;
import com.aig.common.dto.AuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void saveAudit(AuditEvent event) {

        AuditLog auditLog = AuditLog.builder()
                .documentId(event.getDocumentId())
                .documentName(event.getDocumentName())
                .documentType(event.getDocumentType())
                .serviceName(event.getServiceName())
                .eventType(event.getEventType())
                .status(event.getStatus())
                .description(event.getDescription())
                .eventTime(event.getEventTime())
                .build();

        auditLogRepository.save(auditLog);

        log.info("Audit Event saved successfully for document id : {}",
                event.getDocumentId());
    }

    @Override
    public void saveAudit(AuditLog auditLog) {

        auditLogRepository.save(auditLog);

        log.info("Audit Log saved successfully with id : {}",
                auditLog.getId());
    }

    @Override
    public List<AuditLog> getAllLogs() {

        log.info("Fetching all audit logs");

        return auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getAuditByDocumentId(Long documentId) {

        log.info("Fetching audit logs for document id : {}", documentId);

        return auditLogRepository.findByDocumentId(documentId);
    }

    @Override
    public List<AuditLog> getAuditByEventType(String eventType) {

        log.info("Fetching audit logs for event type : {}", eventType);

        return auditLogRepository.findByEventType(eventType);
    }

    @Override
    public List<AuditLog> getAuditByServiceName(String serviceName) {

        log.info("Fetching audit logs for service name : {}", serviceName);

        return auditLogRepository.findByServiceName(serviceName);
    }
}