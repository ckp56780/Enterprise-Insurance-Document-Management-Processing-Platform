package com.search.service.controller;
import com.search.service.dto.DocumentMetadataDto;
import com.search.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<DocumentMetadataDto> getAll() {

        return searchService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public DocumentMetadataDto getById(
            @PathVariable Long id) {

        return searchService.getDocumentById(id);
    }

    @GetMapping("/name/{name}")
    public List<DocumentMetadataDto> searchByName(
            @PathVariable String name) {

        return searchService.searchByName(name);
    }
}