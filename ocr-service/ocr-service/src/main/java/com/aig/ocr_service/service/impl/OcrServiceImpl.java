package com.aig.ocr_service.service.impl;

import com.aig.ocr_service.dto.OcrResponse;
import com.aig.ocr_service.enity.OcrDocument;
import com.aig.common.dto.*;
import com.aig.ocr_service.repository.OcrRepository;
import com.aig.ocr_service.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OcrServiceImpl implements OcrService {

    private final OcrRepository repository;

    @Override
    public void processDocument(DocumentCreatedEvent event) {

        log.info(
                "Processing OCR for document : {}",
                event.getDocumentName());

        OcrDocument document =
                OcrDocument.builder()
                        .documentId(event.getDocumentId())
                        .documentName(event.getDocumentName())
                        .extractedText(
                                "Dummy OCR Text extracted from "
                                        + event.getDocumentName()
                        )
                        .status("COMPLETED")
                        .processedTime(
                                LocalDateTime.now()
                        )
                        .build();

        repository.save(document);

        log.info(
                "OCR Document Saved Successfully");
    }

    @Override
    public List<OcrResponse> getAllDocuments() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OcrResponse getByDocumentId(
            Long documentId) {

        OcrDocument document =
                repository.findByDocumentId(documentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "OCR Data Not Found For Document Id : "
                                                + documentId));

        return mapToResponse(document);
    }

    private OcrResponse mapToResponse(
            OcrDocument document) {

        return OcrResponse.builder()
                .documentId(document.getDocumentId())
                .documentName(document.getDocumentName())
                .extractedText(document.getExtractedText())
                .status(document.getStatus())
                .processedTime(document.getProcessedTime())
                .build();
    }
}