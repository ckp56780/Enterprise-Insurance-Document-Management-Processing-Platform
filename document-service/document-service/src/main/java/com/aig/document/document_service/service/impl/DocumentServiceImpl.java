package com.aig.document.document_service.service.impl;

import com.aig.common.dto.AuditEvent;
import com.aig.document.document_service.dto.DocumentRequest;
import com.aig.document.document_service.dto.DocumentResponse;
import com.aig.document.document_service.entity.Document;
import com.aig.document.document_service.event.DocumentCreatedEvent;
import com.aig.document.document_service.exception.ResourceNotFoundException;
import com.aig.document.document_service.mapper.DocumentMapper;
import com.aig.document.document_service.producer.DocumentEventProducer;
import com.aig.document.document_service.repositroy.DocumentRepository;
import com.aig.document.document_service.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;
    private final DocumentEventProducer producer;

    @Override
    public DocumentResponse createDocument(DocumentRequest request) {

        log.info("Creating document : {}", request.getDocumentName());

        // Convert DTO to Entity
        Document document = mapper.toEntity(request);

        // Save into database
        Document savedDocument = repository.save(document);

        log.info("Document created successfully with id : {}",
                savedDocument.getId());

        DocumentCreatedEvent documentEvent =
                DocumentCreatedEvent.builder()
                        .documentId(savedDocument.getId())
                        .documentName(savedDocument.getDocumentName())
                        .documentType(savedDocument.getDocumentType())
                        .build();

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

        // Publish to Kafka
        producer.publish(documentEvent, auditEvent);

        log.info("Audit event published for document id : {}",
                savedDocument.getId());

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
                                "Document Not Found : " + id));

        repository.delete(document);

        log.info("Document deleted successfully with id : {}", id);
    }
}