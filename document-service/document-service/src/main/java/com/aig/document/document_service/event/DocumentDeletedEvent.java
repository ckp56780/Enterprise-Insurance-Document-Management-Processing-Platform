package com.aig.document.document_service.event;

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