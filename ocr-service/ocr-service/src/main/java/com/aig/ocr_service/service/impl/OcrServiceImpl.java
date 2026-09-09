package com.aig.ocr_service.service.impl;

import com.aig.ocr_service.dto.OcrResponse;
import com.aig.ocr_service.enity.OcrDocument;
import com.aig.common.dto.*;
import com.aig.ocr_service.producer.AuditEventProducer;
import com.aig.ocr_service.producer.OcrEventProducer;
import com.aig.ocr_service.repository.OcrRepository;
import com.aig.ocr_service.service.Ocrservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;


import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class OcrServiceImpl implements Ocrservice {

    private final OcrRepository repository;
    private final TextractClient textractClient;
    private final OcrEventProducer ocrEventProducer;
    private final AuditEventProducer auditEventProducer;


    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public void processDocument(
            DocumentCreatedEvent event) {

        try {

            log.info(
                    "Received File URL : {}",event.getFileUrl());
            String extractedText =extractText(event);

            log.info("Received File URL : {}",event.getFileUrl());
            OcrDocument document =
                    OcrDocument.builder()
                            .documentId(event.getDocumentId())
                            .documentName(event.getDocumentName())
                            .extractedText(extractedText)
                            .status("COMPLETED")
                            .processedTime(LocalDateTime.now())
                            .build();

            repository.save(document);

            //Publish Event After Save to notification service
            OcrCompletedEvent ocrEvent =
                    OcrCompletedEvent.builder()
                            .documentId(event.getDocumentId())
                            .documentName(event.getDocumentName())
                            .extractedText(extractedText)
                            .status("COMPLETED")
                            .processedTime(LocalDateTime.now())
                            .build();
            ocrEventProducer.publishOcrCompletedEvent(ocrEvent);

            //this is for the audit service
            AuditEvent auditEvent =
                    AuditEvent.builder()
                            .documentId(event.getDocumentId())
                            .documentName(event.getDocumentName())
                            .documentType(event.getDocumentType())
                            .serviceName("OCR_SERVICE")
                            .eventType("OCR_COMPLETED")
                            .status("SUCCESS")
                            .description(
                                    "OCR completed successfully using AWS Textract")
                            .eventTime(LocalDateTime.now())
                            .build();

            auditEventProducer.publishAuditEvent(
                    auditEvent);

        } catch (Exception ex) {

            log.error(
                    "OCR Processing Failed",
                    ex);

            OcrDocument document =
                    OcrDocument.builder()
                            .documentId(event.getDocumentId())
                            .documentName(event.getDocumentName())
                            .status("FAILED")
                            .processedTime(LocalDateTime.now())
                            .build();

            repository.save(document);

            //for audit for negative case
            AuditEvent auditEvent =
                    AuditEvent.builder()
                            .documentId(event.getDocumentId())
                            .documentName(event.getDocumentName())
                            .documentType(event.getDocumentType())
                            .serviceName("OCR_SERVICE")
                            .eventType("OCR_FAILED")
                            .status("FAILED")
                            .description(ex.getMessage())
                            .eventTime(LocalDateTime.now())
                            .build();

            auditEventProducer.publishAuditEvent(
                    auditEvent);
        }
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

    @Override
    public String extractText(
            DocumentCreatedEvent event) {

        String objectKey =
                event.getFileUrl()
                        .substring(
                                event.getFileUrl()
                                        .lastIndexOf("/") + 1);

        Document document =
                Document.builder()
                        .s3Object(
                                S3Object.builder()
                                        .bucket(bucketName)
                                        .name(objectKey)
                                        .build())
                        .build();

        DetectDocumentTextRequest request =
                DetectDocumentTextRequest.builder()
                        .document(document)
                        .build();

        DetectDocumentTextResponse response =
                textractClient.detectDocumentText(
                        request);

        StringBuilder text =
                new StringBuilder();

        response.blocks()
                .stream()
                .filter(block ->
                        block.blockType()
                                == BlockType.LINE)
                .forEach(block ->
                        text.append(block.text())
                                .append("\n"));

        return text.toString();
    }
}