package com.aig.document.document_service.service;

import com.aig.document.document_service.dto.DocumentRequest;
import com.aig.document.document_service.dto.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    DocumentResponse createDocument(
            DocumentRequest request);

    DocumentResponse uploadDocument(
            MultipartFile file);

    DocumentResponse getDocumentById(Long id);

    List<DocumentResponse> getAllDocuments();

    void deleteDocument(Long id);
}