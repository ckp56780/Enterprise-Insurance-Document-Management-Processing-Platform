package com.aig.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreatedEvent {
    private Long documentId;
    private String documentName;
    private String documentType;
    private String status;
}