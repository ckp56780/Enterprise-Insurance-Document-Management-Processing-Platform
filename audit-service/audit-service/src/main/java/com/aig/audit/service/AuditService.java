package com.aig.audit.service;

import com.aig.audit.entity.AuditLog;
import com.aig.common.dto.AuditEvent;

import java.util.List;

public interface AuditService {

    void saveAudit(AuditEvent event);

    void saveAudit(AuditLog event);

    List<AuditLog> getAllLogs();

    List<AuditLog> getAuditByDocumentId(Long documentId);

    List<AuditLog> getAuditByEventType(String eventType);

    List<AuditLog> getAuditByServiceName(String serviceName);
}