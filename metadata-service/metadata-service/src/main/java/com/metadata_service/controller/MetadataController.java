package com.metadata_service.controller;

import com.metadata_service.entity.DocumentMetadata;
import com.metadata_service.service.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataService metadataService;

    @GetMapping
    public List<DocumentMetadata> getAllDocuments() {
        return metadataService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public DocumentMetadata getById(
            @PathVariable Long id) {

        return metadataService.getDocumentById(id);
    }

    @GetMapping("/name/{name}")
    public List<DocumentMetadata> searchByName(
            @PathVariable String name) {

        return metadataService.searchByName(name);
    }
}