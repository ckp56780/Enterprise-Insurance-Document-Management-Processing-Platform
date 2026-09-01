package com.aig.document.document_service.mapper;

import com.aig.document.document_service.dto.DocumentRequest;
import com.aig.document.document_service.dto.DocumentResponse;
import com.aig.document.document_service.entity.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DocumentMapper {

    // Entity -> Response DTO
    public DocumentResponse toResponse(Document document) {

        return DocumentResponse.builder()
                .id(document.getId())
                .documentName(document.getDocumentName())
                .documentType(document.getDocumentType())
                .status(document.getStatus())
                .build();
    }

    public Document toEntity(DocumentRequest request) {

        return Document.builder()
                .documentName(request.getDocumentName())
                .documentType(request.getDocumentType())
                .filePath(request.getFilePath())
                .status("UPLOADED")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}