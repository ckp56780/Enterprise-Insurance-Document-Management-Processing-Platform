package com.aig.audit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String eventType;

    private Long documentId;

    private String documentName;

    private String documentType;

    private String status;

    private String description;

    private LocalDateTime eventTime;
}