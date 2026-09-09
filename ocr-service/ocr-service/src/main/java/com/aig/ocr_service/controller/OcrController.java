package com.aig.ocr_service.controller;

import com.aig.ocr_service.dto.OcrResponse;
import com.aig.ocr_service.service.Ocrservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//this layer for fake/dummy
@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class OcrController {

    private final Ocrservice ocrservice;

    @GetMapping
    public ResponseEntity<List<OcrResponse>>
    getAllDocuments() {

        return ResponseEntity.ok(
                ocrservice.getAllDocuments());
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<OcrResponse>
    getByDocumentId(
            @PathVariable Long documentId) {

        return ResponseEntity.ok(
                ocrservice.getByDocumentId(
                        documentId));
    }
}