package com.aig.ocr_service.controller;

import com.aig.ocr_service.dto.OcrResponse;
import com.aig.ocr_service.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;

    @GetMapping
    public ResponseEntity<List<OcrResponse>>
    getAllDocuments() {

        return ResponseEntity.ok(
                ocrService.getAllDocuments());
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<OcrResponse>
    getByDocumentId(
            @PathVariable Long documentId) {

        return ResponseEntity.ok(
                ocrService.getByDocumentId(
                        documentId));
    }
}