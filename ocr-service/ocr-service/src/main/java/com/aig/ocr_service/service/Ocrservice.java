package com.aig.ocr_service.service;

import com.aig.common.dto.DocumentCreatedEvent;
import com.aig.ocr_service.dto.OcrResponse;

import java.util.List;

public interface Ocrservice {

    //dummy
    void processDocument(DocumentCreatedEvent event);

    List<OcrResponse> getAllDocuments();

    OcrResponse getByDocumentId(
            Long documentId);

    String extractText(DocumentCreatedEvent event);
}