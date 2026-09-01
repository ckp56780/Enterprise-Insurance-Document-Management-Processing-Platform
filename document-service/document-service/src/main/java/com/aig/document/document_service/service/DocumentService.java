package com.aig.document.document_service.service;

import com.aig.document.document_service.dto.DocumentRequest;
import com.aig.document.document_service.dto.DocumentResponse;

import java.util.List;

public interface DocumentService {

    DocumentResponse createDocument(
            DocumentRequest request);

    DocumentResponse getDocumentById(Long id);

    List<DocumentResponse> getAllDocuments();

    void deleteDocument(Long id);
}