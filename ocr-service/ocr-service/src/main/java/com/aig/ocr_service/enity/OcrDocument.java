package com.aig.ocr_service.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocr_documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcrDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long documentId;

    private String documentName;

    @Column(columnDefinition = "TEXT")
    private String extractedText;

    private String status;

    private LocalDateTime processedTime;
}