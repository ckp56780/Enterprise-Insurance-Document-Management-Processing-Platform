package com.search.service.service;
import com.search.service.dto.DocumentMetadataDto;
import lombok.RequiredArgsConstructor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final WebClient webClient;

    public List<DocumentMetadataDto> getAllDocuments() {

        return webClient.get()
                .uri("/metadata")
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                                        List<DocumentMetadataDto>>() {
                        })
                .block();
    }

    public DocumentMetadataDto getDocumentById(
            Long documentId) {

        return webClient.get()
                .uri("/metadata/{id}", documentId)
                .retrieve()
                .bodyToMono(DocumentMetadataDto.class)
                .block();
    }

    public List<DocumentMetadataDto> searchByName(
            String documentName) {

        return webClient.get()
                .uri("/metadata/name/{name}",
                        documentName)
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                List<DocumentMetadataDto>>() {
                        })
                .block();
    }
}