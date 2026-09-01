package com.aig.document.document_service.controller;
import com.aig.document.document_service.dto.DocumentRequest;
import com.aig.document.document_service.dto.DocumentResponse;
import com.aig.document.document_service.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

    private final DocumentService service;

    @PostMapping
    public ResponseEntity<DocumentResponse>
    createDocument(
            @Valid
            @RequestBody
            DocumentRequest request) {

        return ResponseEntity.status(
                HttpStatus.CREATED)
                .body(service.createDocument(
                        request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteDocument(@PathVariable Long id) {

        service.deleteDocument(id);

        return ResponseEntity.ok(
                "Document Deleted");
    }
    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocumentById(
@PathVariable Long id) {

        log.info("Received request for document id : {}", id);

        DocumentResponse response = service.getDocumentById(id);

        return ResponseEntity.ok(response);
    }
    //Method     URL
    // POST      http://localhost:8080/api/documents
    // GET       http://localhost:8080/api/documents
    // GET       http://localhost:8080/api/documents/1
    // PUT       http://localhost:8080/api/documents/1
    // DELETE    http://localhost:8080/api/documents/1
}