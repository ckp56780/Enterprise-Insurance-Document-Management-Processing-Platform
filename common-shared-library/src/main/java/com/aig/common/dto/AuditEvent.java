package com.aig.common.dto;

import lombok.*;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditEvent {

    @NotNull(message = "Document Id cannot be null")
    private Long documentId;

    @NotBlank(message = "Document Name cannot be blank")
    private String documentName;

    @NotBlank(message = "Document Type cannot be blank")
    private String documentType;

    @NotBlank(message = "Service Name cannot be blank")
    private String serviceName;

    @NotBlank(message = "Event Type cannot be blank")
    private String eventType;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    private String description;

    @NotNull(message = "Event Time cannot be null")
    private LocalDateTime eventTime;
}