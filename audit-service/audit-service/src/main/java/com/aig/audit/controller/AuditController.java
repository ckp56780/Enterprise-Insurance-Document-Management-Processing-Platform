package com.aig.audit.controller;
import com.aig.audit.entity.AuditLog;
import com.aig.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditService.getAllLogs();
    }

    @GetMapping("/document/{documentId}")
    public List<AuditLog> getByDocumentId(
            @PathVariable Long documentId) {

        return auditService.getAuditByDocumentId(documentId);
    }

    @GetMapping("/event/{eventType}")
    public List<AuditLog> getByEventType(
            @PathVariable String eventType) {

        return auditService.getAuditByEventType(eventType);
    }

    @GetMapping("/service/{serviceName}")
    public List<AuditLog> getByServiceName(
            @PathVariable String serviceName) {

        return auditService.getAuditByServiceName(serviceName);
    }
}