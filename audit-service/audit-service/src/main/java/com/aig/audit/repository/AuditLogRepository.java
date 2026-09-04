package com.aig.audit.repository;

import com.aig.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByDocumentId(Long documentId);

    List<AuditLog> findByEventType(String eventType);

    List<AuditLog> findByServiceName(String serviceName);
}