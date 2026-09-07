package com.aig.ocr_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OcrRequest {

    private Long documentId;
}