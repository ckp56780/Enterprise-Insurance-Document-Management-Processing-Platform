package com.metadata_service.service;

import com.metadata_service.entity.DocumentMetadata;
import com.metadata_service.event.DocumentCreatedEvent;
import com.metadata_service.repository.DocumentMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final DocumentMetadataRepository repository;

    public void saveMetadata(
            DocumentCreatedEvent event) {

        DocumentMetadata metadata =
                new DocumentMetadata();

        metadata.setDocumentId(
                event.getDocumentId());

        metadata.setDocumentName(
                event.getDocumentName());

        metadata.setDocumentType(
                event.getDocumentType());

        metadata.setStatus(
                event.getStatus());

        repository.save(metadata);
    }

    //search service call krega isliye isko banana padega
    public List<DocumentMetadata> getAllDocuments() {
        return repository.findAll();
    }

    public DocumentMetadata getDocumentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));
    }

    public List<DocumentMetadata> searchByName(String name) {
        return repository
                .findByDocumentNameContainingIgnoreCase(name);
    }
}