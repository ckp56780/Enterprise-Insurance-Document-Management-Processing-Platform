package com.aig.common.dto;

import lombok.*;
//this is we are creating because we need to publish to audit
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDeletedEvent {

    private Long documentId;
    private String documentName;
    private String documentType;
    private String status;
}