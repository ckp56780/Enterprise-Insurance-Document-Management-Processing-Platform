package com.aig.document.document_service.service.impl;

import com.aig.common.dto.AuditEvent;
import com.aig.document.document_service.dto.DocumentRequest;
import com.aig.document.document_service.dto.DocumentResponse;
import com.aig.document.document_service.entity.Document;
import com.aig.common.dto.*;
import com.aig.document.document_service.exception.ResourceNotFoundException;
import com.aig.document.document_service.mapper.DocumentMapper;
import com.aig.document.document_service.producer.DocumentEventProducer;
import com.aig.document.document_service.repositroy.DocumentRepository;
import com.aig.document.document_service.service.DocumentService;
import com.aig.document.document_service.service.storage.S3StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;
    private final DocumentEventProducer producer;
    private final S3StorageService s3StorageService;

    @Override
    public DocumentResponse createDocument(DocumentRequest request) {

        log.info("Creating document : {}", request.getDocumentName());

        // Convert DTO to Entity
        Document document = mapper.toEntity(request);
        Document savedDocument = repository.save(document);
        log.info("Document created successfully with id : {}",
                savedDocument.getId());

        // Create Business Event-which will produce for all services
        DocumentCreatedEvent documentEvent =
                DocumentCreatedEvent.builder()
                        .documentId(savedDocument.getId())
                        .documentName(savedDocument.getDocumentName())
                        .documentType(savedDocument.getDocumentType())
                        .fileUrl(savedDocument.getFileUrl())
                        .status(savedDocument.getStatus())
                        .build();

        // Create Audit Event-only for audit
        AuditEvent auditEvent =
                AuditEvent.builder()
                        .documentId(savedDocument.getId())
                        .documentName(savedDocument.getDocumentName())
                        .documentType(savedDocument.getDocumentType())
                        .serviceName("DOCUMENT_SERVICE")
                        .eventType("DOCUMENT_CREATED")
                        .status("SUCCESS")
                        .description("Document uploaded successfully")
                        .eventTime(LocalDateTime.now())
                        .build();

        // Publish Business Event-to all services
        producer.publishDocumentCreatedEvent(documentEvent);

        // Publish Audit Event
        producer.publishAuditEvent(auditEvent);
        log.info("Audit event published for document id : {}",
                savedDocument.getId());

        return mapper.toResponse(savedDocument);
    }
    @Override
    public DocumentResponse uploadDocument(
            MultipartFile file) {

        String s3Url =
        s3StorageService.uploadFile(file);
        String fileName =
        file.getOriginalFilename();
        String documentType = "unknown";
        if (fileName != null && fileName.contains(".")) {
            documentType =
            fileName.substring(
                    fileName.lastIndexOf(".") + 1)
                    .toLowerCase();
        }
        Document document =
                Document.builder()
                        .documentName(
                                file.getOriginalFilename())
                        .documentType(documentType)
                        .fileUrl(s3Url)
                        .status("UPLOADED")
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build();

        Document savedDocument =
                repository.save(document);

// Business Event--it will send it only to all services
        DocumentCreatedEvent documentEvent =
                DocumentCreatedEvent.builder()
                        .documentId(savedDocument.getId())
                        .documentName(savedDocument.getDocumentName())
                        .documentType(savedDocument.getDocumentType())
                        .fileUrl(savedDocument.getFileUrl())
                        .status(savedDocument.getStatus())
                        .build();

        producer.publishDocumentCreatedEvent(documentEvent);

// Audit Event-only for audit
        AuditEvent auditEvent =
                AuditEvent.builder()
                        .documentId(savedDocument.getId())
                        .documentName(savedDocument.getDocumentName())
                        .documentType(savedDocument.getDocumentType())
                        .serviceName("DOCUMENT_SERVICE")
                        .eventType("DOCUMENT_CREATED")
                        .status("SUCCESS")
                        .description("Document uploaded successfully")
                        .eventTime(LocalDateTime.now())
                        .build();

        producer.publishAuditEvent(auditEvent);

        return mapper.toResponse(savedDocument);
    }


    @Override
    public DocumentResponse getDocumentById(Long id) {

        log.info("Fetching document with id : {}", id);

        Document document = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Document Not Found : " + id));

        return mapper.toResponse(document);
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {

        log.info("Fetching all documents");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void deleteDocument(Long id) {

        log.info("Deleting document with id : {}", id);

        Document document = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Document Not Found with id : " + id));

        //1. Delete from database
        repository.delete(document);
        log.info("Document deleted successfully with id : {}", id);

        //2.this we are adding because need to notify all other services.
        DocumentDeletedEvent documentEvent = DocumentDeletedEvent.builder().documentId(document.getId())
                .documentName(document.getDocumentName())
                .documentType(document.getDocumentType())
                .status(document.getStatus())
                .build();
        producer.publishDocumentDeletedEvent(documentEvent);

        //3.this is for only audit to send
        AuditEvent auditEvent = AuditEvent.builder().serviceName("DOCUMENT_SERVICE")
                .eventType("DOCUMENT_DELETED")
                .documentId(document.getId())
                .documentName(document.getDocumentName())
                .documentType(document.getDocumentType())
                .status(document.getStatus())
                .description("Document deleted successfully")
                .eventTime(LocalDateTime.now())
                .build();
        producer.publishAuditEvent(auditEvent);



    }
}