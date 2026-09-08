package com.aig.document.document_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentResponse {

    private Long id;

    private String documentName;

    private String documentType;

    private String status;
    private String fileUrl;
}