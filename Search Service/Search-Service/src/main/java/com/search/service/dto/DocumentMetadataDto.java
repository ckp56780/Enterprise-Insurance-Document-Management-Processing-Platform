package com.search.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadataDto {

    private Long documentId;

    private String documentName;

    private String documentType;

    private String status;
}