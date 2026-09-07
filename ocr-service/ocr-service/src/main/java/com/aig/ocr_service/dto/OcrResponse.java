package com.aig.ocr_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OcrResponse {

    private Long documentId;

    private String documentName;

    private String extractedText;

    private String status;

    private LocalDateTime processedTime;
}