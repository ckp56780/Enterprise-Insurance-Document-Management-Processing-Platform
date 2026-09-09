package com.aig.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcrCompletedEvent {

    private Long documentId;

    private String documentName;

    private String extractedText;

    private String status;

    private LocalDateTime processedTime;
}