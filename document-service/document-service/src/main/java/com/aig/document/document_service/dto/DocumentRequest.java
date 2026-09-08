package com.aig.document.document_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentRequest {

    @NotBlank(message = "Document Name is required")
    private String documentName;

    @NotBlank(message = "Document Type is required")
    private String documentType;

    @NotBlank(message = "File Path is required")
    private String fileUrl;
}